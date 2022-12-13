package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.dto.PacienteResponseDto;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import com.dh.clinicaodonto.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

   @Autowired
   private PacienteServiceImpl pacienteService;

   @GetMapping()
   public ResponseEntity<List<PacienteResponseDto>> findAllPacientes(){
      return pacienteService.findAllPacientes();
   }

   @GetMapping("{rg}")
   public ResponseEntity<PacienteResponseDto> findByRg(@PathVariable String rg){
      return pacienteService.findByRg(rg);
   }

   @PostMapping()
   @ResponseBody
   public ResponseEntity<PacienteDto> savePaciente(@RequestBody @Valid PacienteDto pacienteDto){
      return pacienteService.savePaciente(pacienteDto);
   }

   @PatchMapping()
   @ResponseBody
   public ResponseEntity<PacienteResponseDto> updatePacienteByRg(@RequestBody @Valid PacienteDto pacienteDto){
      return pacienteService.updatePacienteByRg(pacienteDto);
   }

   @DeleteMapping("{rg}")
   public ResponseEntity<String> deletePaciente(@PathVariable String rg) throws ResourceNotFoundException {
      return pacienteService.deletePaciente(rg);
   }

}
