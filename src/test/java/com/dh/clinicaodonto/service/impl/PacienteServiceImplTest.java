package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Endereco;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.EnderecoDto;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

   ObjectMapper mapper = new ObjectMapper();
   PacienteDto pacienteRetorno = new PacienteDto();

//   @Test
//   void findAllPacientes() {
//      assertTrue(service.findAllPacientes().getBody().size() > 0);
//   }
//
//   @Test
//   void findByRg() {
//      PacienteDto paciente = service.findByRg("383959408").getBody();
//      assertEquals("Elza",paciente.getNome());
//   }
//
//   @Test
//   void savePaciente() {
//      EnderecoDto endereco = new EnderecoDto("05546-030","Praça Professor Vasco de Andrade","427","Jardim Cláudia","São Paulo","SP");
//      PacienteDto paciente = new PacienteDto("Neymar","Samuel Luan Almada","252713334", LocalDate.now(),endereco);
//      mapper.registerModule(new JavaTimeModule());
//      pacienteRetorno = service.savePaciente(paciente).getBody();
//      assertTrue(pacienteRetorno.getRg().equalsIgnoreCase("252713334"));
//      assertEquals(paciente.getNome(),pacienteRetorno.getNome());
//   }
//
//   @Test
//   void updatePacienteByRg() {
//
//
//      EnderecoDto endereco1 = new EnderecoDto("05546-030","Praça Professor Vasco de Andrade","427","Jardim Cláudia","São Paulo","SP");
//      PacienteDto paciente1 = new PacienteDto("Calebe","Samuel Luan Almada","252713333", LocalDate.now(),endereco1);
//
//      assertEquals(200,service.updatePacienteByRg(paciente1).getStatusCode().value());
//
//      EnderecoDto endereco2 = new EnderecoDto("05546-030","Praça Professor Vasco de Andrade","427","Jardim Cláudia","São Paulo","SP");
//      PacienteDto paciente2 = new PacienteDto( "Timbó","Samuel Luan Almada","252713335", LocalDate.now(),endereco2);
//      assertEquals(400,service.updatePacienteByRg(paciente2).getStatusCode().value());
//   }
//
//   @Test
//   void deletePaciente() {
//      assertEquals(200,service.deletePaciente("419234664").getStatusCode().value());
//      assertEquals(400,service.deletePaciente("383959408").getStatusCode().value());
//   }
}