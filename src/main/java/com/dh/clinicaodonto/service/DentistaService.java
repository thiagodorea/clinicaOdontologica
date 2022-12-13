package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.DentistaResponseDto;
import com.dh.clinicaodonto.dto.PacienteDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DentistaService {

    List<DentistaResponseDto> findAllDenstistas();
    ResponseEntity<DentistaResponseDto> findByMatricula(String matricula);
    ResponseEntity<DentistaResponseDto> saveDentista(DentistaDto dentistaDto);
    ResponseEntity<DentistaResponseDto> updateDentistaByMatricula(DentistaDto dentistaDto);
    ResponseEntity<String> deleteDentista(String matricula);
}
