package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.repository.DentistaRepository;
import com.dh.clinicaodonto.service.DentistaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class DentistaServiceImpl implements DentistaService {
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private DentistaRepository dentistaRepository;

    @Override
    public List<DentistaDto> findAllDenstistas() {
        log.info("[DentistaService] [findAllDentistas]");
        List<Dentista> dentistas = dentistaRepository.findAll();
        List<DentistaDto> dentistasDto = new ArrayList<>();
        mapper.registerModule(new JavaTimeModule());
        for(Dentista dentista : dentistas) {
            dentistasDto.add(mapper.convertValue(dentista, DentistaDto.class));
        }
        return dentistasDto;
    }

    @Override
    public ResponseEntity<DentistaDto> findDentistaByMatricula(String matricula) {
        log.info("[DentistaService] [findDentistaById]");
        mapper.registerModule(new JavaTimeModule());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(dentistaRepository.findByMatricula(matricula), DentistaDto.class));
        } catch (Exception e) {
            return new ResponseEntity("não foi localizado o dentista",HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<DentistaDto> saveDentista(Dentista dentista) {
        log.info("[DentistaService] [saveDentista]");

        try {
            mapper.registerModule(new JavaTimeModule());
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(dentistaRepository.save(dentista), DentistaDto.class));
        }catch (Exception e) {
            log.error("[DentistaService] [saveDentista] Não foi possível salvar o dentista");
            return new ResponseEntity("Não foi possível salvar o dentista "+ dentista.getNome(),HttpStatus.BAD_REQUEST);
        }

    }
    @Override
    public ResponseEntity<DentistaDto> updateDentistaById(Dentista dentista) {
        log.info("[DentistaService] [updateDentistaById]");
        try{
            DentistaDto dentistaDto = findDentistaByMatricula(dentista.getMatricula()).getBody();
            mapper.registerModule(new JavaTimeModule());
            return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(dentistaRepository.save(dentista), DentistaDto.class));
        }catch (Exception e){
            log.error("[DentistaService] [updateDentistaById] Dentista não foi encontrado", e);
            return new ResponseEntity("Dentista não foi encontrado",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteDentista(String matricula) {
        log.info("[DentistaService] [deleteDentista]");
        try {
            Dentista dentista = dentistaRepository.findByMatricula(matricula);
            dentistaRepository.deleteById(dentista.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Dentista excluido com sucesso.");
        }catch (Exception e){
            log.error("[DentistaService] [deleteDentista] Erro ao excluir Dentista", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o Dentista" );
        }
    }
}
