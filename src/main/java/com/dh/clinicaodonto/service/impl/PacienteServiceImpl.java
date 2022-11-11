package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.repository.PacienteRepository;
import com.dh.clinicaodonto.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

   @Autowired
   private PacienteRepository pacienteRepository;

   @Override
   public List<Paciente> listarPacientes() {
      return pacienteRepository.findAll();
   }
}
