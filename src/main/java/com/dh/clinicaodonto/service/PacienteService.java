package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PacienteService {

   List<Paciente> findAllPacientes();
   Paciente findPacienteById(Long id);
   Paciente savePaciente(Paciente paciente);
   Paciente updatePacienteById(Paciente paciente);
   void deletePaciente(Long id);

}
