package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.controller.PacienteController;
import com.dh.clinicaodonto.domain.Endereco;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.repository.PacienteRepository;
import com.dh.clinicaodonto.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceImplTest {

   @Autowired
   private PacienteService service;

   Paciente pacienteRetorno = new Paciente();

   @BeforeEach
   void doBefore(){
      Paciente pacienteRetorno = new Paciente();
   }

   @Test
   void findAllPacientes() {

      assertTrue(service.findAllPacientes().size() > 0);
   }

   @Test
   void findPacienteById() {
   }

   @Test
   void savePaciente() {
      Endereco endereco = new Endereco(1L,"05546-030","Praça Professor Vasco de Andrade","427","Jardim Cláudia","São Paulo","SP");
      Paciente paciente = new Paciente( 1L,"Calebe","Samuel Luan Almada","25.271.333-3", LocalDate.now(),endereco);
      pacienteRetorno = service.savePaciente(paciente);
      assertTrue(paciente.getId() > 0);
      assertEquals(paciente,pacienteRetorno);
   }

   @Test
   void updatePacienteById() {
   }

   @Test
   void deletePaciente() {
   }
}