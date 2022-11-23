package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

   @Autowired
   private PacienteServiceImpl pacienteService;

   @GetMapping()
   public List<PacienteDto> findAllPacientes(){
      return pacienteService.findAllPacientes();
   }

   @GetMapping("{id}")
   public ResponseEntity<PacienteDto> findPacienteById(@PathVariable Long id){
      return pacienteService.findPacienteById(id);
   }
   @PostMapping()
   @ResponseBody
   public ResponseEntity<PacienteDto> savePaciente(@RequestBody Paciente paciente){
      return pacienteService.savePaciente(paciente);
   }

   @PutMapping()
   @ResponseBody
   public ResponseEntity<PacienteDto> updatePacienteById(@RequestBody Paciente paciente){
      return pacienteService.updatePacienteById(paciente);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<String> deletePaciente(@PathVariable Long id){
      return pacienteService.deletePaciente(id);
   }

}
