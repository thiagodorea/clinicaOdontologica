package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Endereco;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.domain.Perfil;
import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.EnderecoDto;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.dto.PacienteResponseDto;
import com.dh.clinicaodonto.dto.PerfilDto;
import com.dh.clinicaodonto.dto.UsuarioNovoDto;
import com.dh.clinicaodonto.repository.PacienteRepository;
import com.dh.clinicaodonto.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class PacienteServiceImpl implements PacienteService {

   @Autowired
   private PacienteRepository pacienteRepository;

   @Autowired
   private ConsultaServiceImpl consultaService;

   ObjectMapper mapper = new ObjectMapper();
   BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

   @Override
   public ResponseEntity<List<PacienteResponseDto>> findAllPacientes() {
      log.info("[PacienteService] [findAllPacientes]");
      mapper.registerModule(new JavaTimeModule());
      try{
         List<Paciente> pacientes = pacienteRepository.findAll();
         List<PacienteResponseDto> pacientesResponseDto = new ArrayList<>();
         for(Paciente paciente : pacientes) {
            pacientesResponseDto.add(mapper.convertValue(paciente, PacienteResponseDto.class));
         }
         if(pacientesResponseDto.isEmpty())
            return new ResponseEntity("Não localizamos nenhum paciente no sistema.",HttpStatus.BAD_REQUEST);

         return ResponseEntity.status(HttpStatus.OK).body(pacientesResponseDto);
      }catch (Exception e){
         return new ResponseEntity("Erro ao buscar pacientes.",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<PacienteResponseDto> findByRg(String rg) {
      log.info("[PacienteService] [findByRg]");
      mapper.registerModule(new JavaTimeModule());
      try{
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.findByRg(rg).get(),PacienteResponseDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [findPacienteById] Paciente não localizado");
         return new ResponseEntity("Paciente não foi localizado",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   @Transactional
   public ResponseEntity<PacienteDto> savePaciente(PacienteDto pacienteDto) {
      log.info("[PacienteService] [savePaciente]");

      try{
         mapper.registerModule(new JavaTimeModule());
         List<PerfilDto> perfisDto = new ArrayList<>();
         perfisDto.add(new PerfilDto(2L,null));
         UsuarioNovoDto usuarioNovoDto = new UsuarioNovoDto();
         usuarioNovoDto.setUsername(pacienteDto.getRg());
         usuarioNovoDto.setPassword(enc.encode(pacienteDto.getUsuario().getPassword()));
         usuarioNovoDto.setPerfis(perfisDto);
         pacienteDto.setDataCadastro(LocalDate.now());
         pacienteDto.setUsuario(usuarioNovoDto);
         Paciente paciente = mapper.convertValue(pacienteDto,Paciente.class);
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(pacienteRepository.save(paciente),PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [savePaciente] Não foi possível salvar o paciente");
         return new ResponseEntity("Não foi possível salvar o paciente "+ pacienteDto.getNome(),HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   @Transactional
   public ResponseEntity <PacienteResponseDto> updatePacienteByRg(PacienteDto pacienteDto) {
      log.info("[PacienteService] [updatePacienteByRg]");
      mapper.registerModule(new JavaTimeModule());
      try{
         Paciente pacienteResponse = responsePacienteByRg(pacienteDto.getRg());
         Endereco endereco = mapper.convertValue(pacienteDto.getEndereco(), Endereco.class);
         if(!Objects.isNull(pacienteResponse.getEndereco()))
            endereco.setId(pacienteResponse.getEndereco().getId());
         Usuario usuario = new Usuario();
         usuario.setId(pacienteResponse.getUsuario().getId());

         Paciente paciente = mapper.convertValue(pacienteDto,Paciente.class);
         paciente.setId(pacienteResponse.getId());
         paciente.setDataCadastro(pacienteResponse.getDataCadastro());
         paciente.setEndereco(endereco);
         paciente.setUsuario(usuario);
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.save(paciente), PacienteResponseDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [updatePacienteById] Erro ao atualizar os dados do paciente: "+pacienteDto.getRg(), e);
         return new ResponseEntity("Não foi possivel atualizar o(a) Paciente: "+pacienteDto.getRg(),HttpStatus.BAD_REQUEST);
      }
   }

   @Override
      public ResponseEntity<String> deletePaciente(String rg) {
      log.info("[PacienteService] [deletePaciente]");
      try {
         Paciente paciente =  responsePacienteByRg(rg);
         if(consultaService.existeConsultaByRg(paciente.getRg()).size() > 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existem consultas registradas para: "+ paciente.getNome() +" "+paciente.getSobrenome());

            pacienteRepository.deleteById(paciente.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Paciente " + paciente.getNome() +" excluido com sucesso.");
      }catch (Exception e){
         log.error("[PacienteService] [deletePaciente] Não foi possível excluir o Paciente", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O paciente: "+rg + " não foi localizado.");
      }
   }

   public Paciente responsePacienteByRg(String rg){
      log.info("[PacienteService] [responsePacienteByRg]");
      return pacienteRepository.findByRg(rg).get();
   }


}
