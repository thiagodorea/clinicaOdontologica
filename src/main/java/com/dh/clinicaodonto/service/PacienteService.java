package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PacienteService {

   List<PacienteDto> findAllPacientes();
   ResponseEntity<PacienteDto> findPacienteById(Long id);
   ResponseEntity<PacienteDto> findByRg(String rg);
   ResponseEntity<PacienteDto> savePaciente(Paciente paciente);
   ResponseEntity<PacienteDto> updatePacienteById(Paciente paciente);
   ResponseEntity<String> deletePaciente(Long id);

}
