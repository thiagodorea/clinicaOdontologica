package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
import com.dh.clinicaodonto.exception.InvalidRegistrationException;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import com.dh.clinicaodonto.service.impl.ConsultaServiceImpl;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

   @Autowired
   private ConsultaServiceImpl consultaService;

   @GetMapping()
   public ResponseEntity<List<ConsultaDto>> findAllConsultas() throws ResourceNotFoundException {
      return consultaService.findAllConsultas();
   }

   @GetMapping("{rg}")
   public ResponseEntity<List<ConsultaDto>> findConsultaByRg(@PathVariable String rg) throws ResourceNotFoundException {
      return consultaService.findConsultaByRg(rg);
   }

   @PostMapping()
   @ResponseBody
   public ResponseEntity<ConsultaDto> saveConsulta(@RequestBody @Valid ConsultaMarcacaoDto consultaMarcacao) throws InvalidRegistrationException {
      return consultaService.saveConsulta(consultaMarcacao);
   }

   @PutMapping()
   @ResponseBody
   public ResponseEntity<ConsultaDto> updateConsultaByRg(@RequestBody @Valid ConsultaMarcacaoDto consultaMarcacao) throws InvalidRegistrationException, ResourceNotFoundException {
      return consultaService.updateConsultaByRg(consultaMarcacao);
   }

   @DeleteMapping()
   public ResponseEntity<String> deleteConsulta(@RequestBody @Valid ConsultaMarcacaoDto consultaMarcacao) throws ResourceNotFoundException {
      return consultaService.deleteConsulta(consultaMarcacao);
   }

}
