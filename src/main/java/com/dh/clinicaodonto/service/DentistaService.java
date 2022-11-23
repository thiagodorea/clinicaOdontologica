package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.DentistaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DentistaService {

    List<DentistaDto> findAllDenstistas();

    ResponseEntity<DentistaDto> findDentistaById(Long id);

    ResponseEntity<DentistaDto> saveDentista(Dentista dentista);

    ResponseEntity<DentistaDto> updateDentistaById(Dentista dentista);

    ResponseEntity<String> deleteDentista(long id);
}
