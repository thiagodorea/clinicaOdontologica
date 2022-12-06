package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Consulta;
import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
import com.dh.clinicaodonto.service.impl.ConsultaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consultas")
@CrossOrigin
public class ConsultaController {

   @Autowired
   private ConsultaServiceImpl consultaService;

   @GetMapping()
   public List<ConsultaDto> findAllConsultas(){
      return consultaService.findAllConsultas();
   }

//   @GetMapping("id/{id}")
//   public ResponseEntity<ConsultaDto> findConsultaById(@PathVariable Long id){
//      return consultaService.findConsultaById(id);
//   }

   @GetMapping("{rg}")
   public ResponseEntity<List<ConsultaDto>> findConsultaByRg(@PathVariable String rg){
      return consultaService.findConsultaByRg(rg);
   }

   @GetMapping("matricula/{matricula}")
   public ResponseEntity<List<ConsultaDto>> findConsultaByMatricula(@PathVariable String matricula){
      return consultaService.findConsultaByMatricula(matricula);
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

   @DeleteMapping()
   public ResponseEntity<String> deleteConsulta(@RequestBody ConsultaMarcacaoDto consultaMarcacao){
      return consultaService.deleteConsulta(consultaMarcacao);
   }

}
