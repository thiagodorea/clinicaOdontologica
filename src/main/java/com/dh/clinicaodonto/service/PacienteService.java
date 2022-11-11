package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PacienteService {

   public List<Paciente> listarPacientes();


}
