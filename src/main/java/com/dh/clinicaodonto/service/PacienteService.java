package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.dto.PacienteResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PacienteService {

   ResponseEntity<List<PacienteResponseDto>> findAllPacientes();
   ResponseEntity<PacienteResponseDto> findByRg(String rg);
   ResponseEntity<PacienteDto> savePaciente(PacienteDto pacienteDto);
   ResponseEntity<PacienteResponseDto> updatePacienteByRg(PacienteDto pacienteDto);
   ResponseEntity<String> deletePaciente(String rg);

}
