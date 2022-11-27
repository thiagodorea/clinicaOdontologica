package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
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
   void findConsultaById() {
      assertEquals("Elza",service.findConsultaById(1l).getBody().getPaciente().getNome());
   }

   @Test
   void findConsultaByRg() {
      assertEquals("Elza",service.findConsultaByRg("383959408").getBody().get(0).getPaciente().getNome());
   }

   @Test
   void saveConsulta() {
      ConsultaMarcacaoDto consultaMarcacaoDto = new ConsultaMarcacaoDto(null, LocalDateTime.now(),"383959408","0000002");
      consultaRetorno = service.saveConsulta(consultaMarcacaoDto).getBody();
      assertEquals("Elza",consultaRetorno.getPaciente().getNome());
   }

   @Test
   void updateConsultaByRg() {
      ConsultaMarcacaoDto consultaMarcacaoDto = new ConsultaMarcacaoDto(1L, LocalDateTime.now(),"383959408","0000001");
      consultaRetorno = service.saveConsulta(consultaMarcacaoDto).getBody();
      assertEquals("Elza",consultaRetorno.getPaciente().getNome());
      assertEquals("Marcela",consultaRetorno.getDentista().getNome());
   }

   @Test
   void deleteConsulta() {
      assertEquals(200,service.deleteConsulta(1l).getStatusCode().value());
      assertEquals(400,service.deleteConsulta(0l).getStatusCode().value());
   }
}