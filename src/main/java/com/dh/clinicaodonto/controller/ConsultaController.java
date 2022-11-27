package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
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

import java.util.List;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

   @Autowired
   private ConsultaServiceImpl consultaService;

   @GetMapping()
   public List<ConsultaDto> findAllConsultas(){
      return consultaService.findAllConsultas();
   }

   @GetMapping("id/{id}")
   public ResponseEntity<ConsultaDto> findConsultaById(@PathVariable Long id){
      return consultaService.findConsultaById(id);
   }

   @GetMapping("{rg}")
   public ResponseEntity<List<ConsultaDto>> findConsultaByRg(@PathVariable String rg){
      return consultaService.findConsultaByRg(rg);
   }

   @PostMapping()
   @ResponseBody
   public ResponseEntity<ConsultaDto> saveConsulta(@RequestBody ConsultaMarcacaoDto consultaMarcacao){
      return consultaService.saveConsulta(consultaMarcacao);
   }

   @PutMapping()
   @ResponseBody
   public ResponseEntity<ConsultaDto> updateConsultaByRg(@RequestBody ConsultaMarcacaoDto consultaMarcacao){
      return consultaService.updateConsultaByRg(consultaMarcacao);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<String> deleteConsulta(@PathVariable Long id){
      return consultaService.deleteConsulta(id);
   }

}