package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Endereco;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PacienteServiceImplTest {

   @Autowired
   private PacienteService service;

   PacienteDto pacienteRetorno = new PacienteDto();

   @Test
   void findAllPacientes() {
      assertTrue(service.findAllPacientes().size() > 0);
   }

   @Test
   void findPacienteById() {
      PacienteDto paciente = service.findPacienteById(1L).getBody();
      assertEquals("Elza",paciente.getNome());
   }

   @Test
   void savePaciente() {
      Endereco endereco = new Endereco(1L,"05546-030","Praça Professor Vasco de Andrade","427","Jardim Cláudia","São Paulo","SP");
      Paciente paciente = new Paciente( 1L,"Calebe","Samuel Luan Almada","25.271.333-3", LocalDate.now(),endereco);
      pacienteRetorno = service.savePaciente(paciente).getBody();
      assertTrue(paciente.getId() > 0);
      assertEquals(paciente.getNome(),pacienteRetorno.getNome());
   }

   @Test
   void updatePacienteById() {


      Endereco endereco1 = new Endereco(2L,"05546-030","Praça Professor Vasco de Andrade","427","Jardim Cláudia","São Paulo","SP");
      Paciente paciente1 = new Paciente( 2L,"Calebe","Samuel Luan Almada","25.271.333-3", LocalDate.now(),endereco1);
      assertEquals(200,service.updatePacienteById(paciente1).getStatusCode().value());

      Endereco endereco2 = new Endereco(0L,"05546-030","Praça Professor Vasco de Andrade","427","Jardim Cláudia","São Paulo","SP");
      Paciente paciente2 = new Paciente( 0L,"Timbó","Samuel Luan Almada","25.271.333-3", LocalDate.now(),endereco2);
      assertEquals(404,service.updatePacienteById(paciente2).getStatusCode().value());
   }

   @Test
   void deletePaciente() {
      assertEquals(200,service.deletePaciente(1l).getStatusCode().value());
      assertEquals(400,service.deletePaciente(0l).getStatusCode().value());
   }
}