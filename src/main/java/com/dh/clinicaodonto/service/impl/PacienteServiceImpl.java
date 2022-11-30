package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.repository.PacienteRepository;
import com.dh.clinicaodonto.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PacienteServiceImpl implements PacienteService {

   @Autowired
   private PacienteRepository pacienteRepository;

   @Autowired
   private ConsultaServiceImpl consultaService;

   ObjectMapper mapper = new ObjectMapper();

   @Override
   public List<PacienteDto> findAllPacientes() {
      log.info("[PacienteService] [findAllPacientes]");
      List<Paciente> pacientes = pacienteRepository.findAll();
      List<PacienteDto> pacientesDto = new ArrayList<>();
      mapper.registerModule(new JavaTimeModule());
      for(Paciente paciente : pacientes) {
         pacientesDto.add(mapper.convertValue(paciente, PacienteDto.class));
      }
      return pacientesDto;
   }

   @Override
   public ResponseEntity<PacienteDto> findPacienteById(Long id) {
      log.info("[PacienteService] [findPacienteById]");
      mapper.registerModule(new JavaTimeModule());
      try{
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.findById(id).get(),PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [findPacienteById] Paciente não localizado");
         return new ResponseEntity("Paciente não foi localizado",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<PacienteDto> findByRg(String rg) {
      log.info("[PacienteService] [findByRg]");
      mapper.registerModule(new JavaTimeModule());
      try{
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.findByRg(rg).get(),PacienteDto.class));
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
         pacienteDto.setDataCadastro(LocalDate.now());
         Paciente paciente = mapper.convertValue(pacienteDto,Paciente.class);
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(pacienteRepository.save(paciente),PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [savePaciente] Não foi possível salvar o paciente");
         return new ResponseEntity("Não foi possível salvar o paciente "+ pacienteDto.getNome(),HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   @Transactional
   public ResponseEntity <PacienteDto> updatePacienteById(PacienteDto pacienteDto) {
      log.info("[PacienteService] [updatePacienteById]");
      Optional<Paciente> pacienteResponse = responsePacienteByRg(pacienteDto.getRg());
      try{
         mapper.registerModule(new JavaTimeModule());
         Paciente paciente = mapper.convertValue(pacienteDto,Paciente.class);
         paciente.setId(pacienteResponse.get().getId());
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [updatePacienteById] Erro ao atualizar os dados do paciente: "+pacienteResponse.get().getNome(), e);
         return new ResponseEntity("Não foi possivel atualizar o(a) Paciente: "+pacienteResponse.get().getNome(),HttpStatus.BAD_REQUEST);
      }
   }

   @Override
      public ResponseEntity<String> deletePaciente(String rg) {
      log.info("[PacienteService] [deletePaciente]");
      Optional<Paciente> paciente =  responsePacienteByRg(rg);
      try {
         if(consultaService.findConsultaByRg(paciente.get().getRg()).getBody().size() > 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existem consultas registradas para: "+ paciente.get().getNome());

            pacienteRepository.deleteById(paciente.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body("Paciente " + paciente.get().getNome() +" excluido com sucesso.");
      }catch (Exception e){
         log.error("[PacienteService] [deletePaciente] Não foi possível excluir o Paciente", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O paciente: "+rg + " não foi localizado.");
      }
   }

   private Optional<Paciente> responsePacienteByRg(String rg){
      log.info("[PacienteService] [responsePacienteByRg]");
      return pacienteRepository.findByRg(rg);
   }


}
