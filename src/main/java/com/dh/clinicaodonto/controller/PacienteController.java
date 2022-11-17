package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("paciente")
public class PacienteController {

   @Autowired
   private PacienteServiceImpl pacienteService;

   @GetMapping("listar")
   public List<Paciente> findAllPacientes(){
      return pacienteService.findAllPacientes();
   }

   @GetMapping("{id}")
   public Paciente findPacienteById(@PathVariable Long id){
      return pacienteService.findPacienteById(id);
   }
   @PostMapping("salvar")
   @ResponseBody
   public ResponseEntity<Paciente> savePaciente(@RequestBody Paciente paciente){
      return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.savePaciente(paciente));
   }

   @PatchMapping("atualizar")
   @ResponseBody
   public Paciente updatePacienteById(@RequestBody Paciente paciente){
      return pacienteService.updatePacienteById(paciente);
   }

   @DeleteMapping("deletar/{id}")
   public ResponseEntity<String> deletePaciente(@PathVariable Long id){
      pacienteService.deletePaciente(id);
      return ResponseEntity.status(HttpStatus.OK).body("Paciente "+id +" deletado com sucesso");
   }

}
