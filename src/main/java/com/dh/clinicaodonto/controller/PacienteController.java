package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

   @Autowired
   private PacienteServiceImpl pacienteService;

   @GetMapping(value = "listar")
   public List<Paciente> listarPacientes(){
      return pacienteService.listarPacientes();
   }

}
