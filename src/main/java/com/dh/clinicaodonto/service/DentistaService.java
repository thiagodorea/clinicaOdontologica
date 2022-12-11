package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.PacienteDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DentistaService {

    List<DentistaDto> findAllDenstistas();
//    ResponseEntity<DentistaDto> findDentistaById(Long id);
    ResponseEntity<DentistaDto> findByMatricula(String matricula);
    ResponseEntity<DentistaDto> saveDentista(DentistaDto dentistaDto);
    ResponseEntity<DentistaDto> updateDentistaByMatricula(DentistaDto dentistaDto);
    ResponseEntity<String> deleteDentista(String matricula);
}
