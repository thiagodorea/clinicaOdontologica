package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Consulta;
import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Endereco;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.DentistaResponseDto;
import com.dh.clinicaodonto.dto.PerfilDto;
import com.dh.clinicaodonto.dto.UsuarioNovoDto;
import com.dh.clinicaodonto.repository.ConsultaRepository;
import com.dh.clinicaodonto.repository.DentistaRepository;
import com.dh.clinicaodonto.service.DentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class DentistaServiceImpl implements DentistaService {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private DentistaRepository dentistaRepository;

    @Lazy
    @Autowired
    private ConsultaServiceImpl consultaService;

    BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
    @Override
    public List<DentistaResponseDto> findAllDenstistas() {
        log.info("[DentistaService] [findAllDentistas]");
        List<Dentista> dentistas = dentistaRepository.findAll();
        List<DentistaResponseDto> dentistasResponseDto = new ArrayList<>();
        mapper.registerModule(new JavaTimeModule());
        for(Dentista dentista : dentistas) {
            dentistasResponseDto.add(mapper.convertValue(dentista, DentistaResponseDto.class));
        }
        return dentistasResponseDto;
    }

    @Override
    public ResponseEntity<DentistaResponseDto> findByMatricula(String matricula) {
        log.info("[DentistaService] [findByMatricula]");
        mapper.registerModule(new JavaTimeModule());
        try{
            return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(dentistaRepository.findByMatricula(matricula).get(), DentistaResponseDto.class));
        }catch(Exception e){
            return new ResponseEntity("O Dentista não foi localizado.",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<DentistaResponseDto> saveDentista(DentistaDto dentistadto) {
        log.info("[DentistaService] [saveDentista]");
        try {
            mapper.registerModule(new JavaTimeModule());
            List<PerfilDto> perfisDto = new ArrayList<>();
            perfisDto.add(new PerfilDto(2L,null));
            UsuarioNovoDto usuarioNovoDto = new UsuarioNovoDto();
            usuarioNovoDto.setUsername(dentistadto.getMatricula());
            usuarioNovoDto.setPassword(enc.encode(dentistadto.getUsuario().getPassword()));
            usuarioNovoDto.setPerfis(perfisDto);
            dentistadto.setUsuario(usuarioNovoDto);
            Optional<Dentista> alreadyExists = dentistaRepository.findByMatricula(dentistadto.getMatricula());
            if(alreadyExists.isEmpty()) {
                Dentista dentista = mapper.convertValue(dentistadto, Dentista.class);
                return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(dentistaRepository.save(dentista), DentistaResponseDto.class));
            }
            log.error("[DentistaService] [saveDentista] matricula "+ dentistadto.getMatricula() + " já cadastrada no sistema");
            return new ResponseEntity( "matricula "+ dentistadto.getMatricula() + " já cadastrada no sistema",HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            log.error("[DentistaService] [saveDentista] Não foi possível salvar o dentista");
            return new ResponseEntity("Não foi possível salvar o dentista "+ dentistadto.getNome(),HttpStatus.BAD_REQUEST);
        }

    }
    @Override
    public ResponseEntity<DentistaResponseDto> updateDentistaByMatricula(DentistaDto dentistadto) {
        log.info("[DentistaService] [updateDentistaById]");
        mapper.registerModule(new JavaTimeModule());
        try{
            Dentista dentistaResponse = responseDentistaByMatricula(dentistadto.getMatricula());
            Usuario usuario = new Usuario();
            usuario.setId(dentistaResponse.getUsuario().getId());
            Dentista dentista = mapper.convertValue(dentistadto,Dentista.class);
            dentista.setId(dentistaResponse.getId());
            dentista.setUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(dentistaRepository.save(dentista), DentistaResponseDto.class));
        }catch (Exception e){
            log.error("[DentistaService] [updateDentistaById] Erro ao atualizar os dados do dentista.", e);
            return new ResponseEntity("Não foi possivel atualizar o(a) Dentista.",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> deleteDentista(String matricula) {
        log.info("[DentistaService] [deleteDentista]");
        try {
            Dentista dentista = responseDentistaByMatricula(matricula);
            if(consultaService. existeConsultaByMatricula(dentista.getMatricula()).size() > 0)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Existem consultas registradas para: "+ dentista.getNome() +" "+dentista.getSobrenome());

            dentistaRepository.deleteById(dentista.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Dentista excluido com sucesso.");

        }catch (Exception e){
            log.error("[DentistaService] [deleteDentista] Erro ao excluir Dentista", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o Dentista" );
        }
    }


    public Dentista responseDentistaByMatricula(String matricula){
        log.info("[DentistaService] [responseDentistaByRg]");
        return dentistaRepository.findByMatricula(matricula).get();
    }
}
