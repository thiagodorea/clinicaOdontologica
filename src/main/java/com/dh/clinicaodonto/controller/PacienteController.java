package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
@CrossOrigin
public class PacienteController {

   @Autowired
   private PacienteServiceImpl pacienteService;

   @GetMapping()
   public List<PacienteDto> findAllPacientes(){
      return pacienteService.findAllPacientes();
   }

   @GetMapping("id/{id}")
   public ResponseEntity<PacienteDto> findPacienteById(@PathVariable Long id){
      return pacienteService.findPacienteById(id);
   }
   @GetMapping("{rg}")
   public ResponseEntity<PacienteDto> findByRg(@PathVariable String rg){
      return pacienteService.findByRg(rg);
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
