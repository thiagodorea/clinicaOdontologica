package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
   public ResponseEntity<PacienteDto> savePaciente(@RequestBody PacienteDto pacienteDto){
      return pacienteService.savePaciente(pacienteDto);
   }

   @PutMapping()
   @ResponseBody
   public ResponseEntity<PacienteDto> updatePacienteById(@RequestBody PacienteDto pacienteDto){
      return pacienteService.updatePacienteById(pacienteDto);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<String> deletePaciente(@PathVariable Long id){
      return pacienteService.deletePaciente(id);
   }

}
