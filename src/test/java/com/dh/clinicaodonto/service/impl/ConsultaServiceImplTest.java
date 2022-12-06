package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
import com.dh.clinicaodonto.dto.DentistaConsultaDto;
import com.dh.clinicaodonto.dto.PacienteConsultaDto;
import com.dh.clinicaodonto.service.ConsultaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ConsultaServiceImplTest {

   @Autowired
   private ConsultaService service;

   ConsultaDto consultaRetorno = new ConsultaDto();

   @Test
   void findAllConsultas() {
      assertTrue(service.findAllConsultas().size() > 0);
   }

   @Test
   void findConsultaByRg() {
      assertEquals("Elza",service.findConsultaByRg("383959408").getBody().get(0).getPaciente().getNome());
   }

   @Test
   void saveConsulta() {
      ConsultaMarcacaoDto consultaMarcacaoDto = new ConsultaMarcacaoDto();
      consultaMarcacaoDto.setDhConsulta(LocalDateTime.now().plusHours(1));
      consultaMarcacaoDto.setRgPaciente("252713333");
      consultaMarcacaoDto.setMatriculaDentista("0000004");
      consultaRetorno = service.saveConsulta(consultaMarcacaoDto).getBody();
      assertEquals("Calebe",consultaRetorno.getPaciente().getNome());
   }

   @Test
   void updateConsultaByRg() {
      ConsultaMarcacaoDto atualizaConsultaMarcacaoDto = new ConsultaMarcacaoDto(1, LocalDateTime.now().plusHours(1),"383959408","0000001");
      ConsultaDto consultaDtoRetorno = service.updateConsultaByRg(atualizaConsultaMarcacaoDto).getBody();
      assertEquals("Elza",consultaDtoRetorno.getPaciente().getNome());
      assertEquals("Marcela",consultaDtoRetorno.getDentista().getNome());
   }

   @Test
   void deleteConsulta() {
      ConsultaMarcacaoDto consultaMarcacaoDto = new ConsultaMarcacaoDto(1, LocalDateTime.now(),"277384904","0000003");
      assertEquals(200,service.deleteConsulta(consultaMarcacaoDto).getStatusCode().value());
      assertEquals(400,service.deleteConsulta(consultaMarcacaoDto).getStatusCode().value());
   }
}