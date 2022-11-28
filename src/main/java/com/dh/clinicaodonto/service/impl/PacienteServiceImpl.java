package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.repository.PacienteRepository;
import com.dh.clinicaodonto.service.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PacienteServiceImpl implements PacienteService {

   @Autowired
   private PacienteRepository pacienteRepository;

   ObjectMapper mapper = new ObjectMapper();

   @Override
   public List<PacienteDto> findAllPacientes() {
      log.info("[PacienteService] [findAllPacientes]");
      List<Paciente> pacientes = pacienteRepository.findAll();
      List<PacienteDto> pacientesDto = new ArrayList<>();
      mapper.registerModule(new JavaTimeModule());
      for(Paciente paciente : pacientes) {
         pacientesDto.add(mapper.convertValue(paciente, PacienteDto.class));
      }
      return pacientesDto;
   }

   @Override
   public ResponseEntity<PacienteDto> findPacienteByRG(String rg) {
      log.info("[PacienteService] [findPacienteById]");
      mapper.registerModule(new JavaTimeModule());
      try{
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.findByRg(rg),PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [findPacienteById] Paciente não localizado");
         return new ResponseEntity("Paciente não foi localizado",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   @Transactional
   public ResponseEntity<PacienteDto> savePaciente(Paciente paciente) {
      log.info("[PacienteService] [savePaciente]");
      try{
         mapper.registerModule(new JavaTimeModule());
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(pacienteRepository.save(paciente),PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [savePaciente] Não foi possível salvar o paciente");
         return new ResponseEntity("Não foi possível salvar o paciente "+ paciente.getNome(),HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity <PacienteDto> updatePacienteById(Paciente paciente) {
      log.info("[PacienteService] [updatePacienteById]");
      try{
            PacienteDto pacienteDto = findPacienteByRG(paciente.getRg()).getBody();
            mapper.registerModule(new JavaTimeModule());
            return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(pacienteRepository.save(paciente), PacienteDto.class));
      }catch (Exception e){
         log.error("[PacienteService] [updatePacienteById] Erro ao atualizar os dados do paciente", e);
         return new ResponseEntity("Paciente não foi encontrado",HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public ResponseEntity<String> deletePaciente(Long id) {
      log.info("[PacienteService] [deletePaciente]");
      try {
         pacienteRepository.deleteById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Paciente " + id +" excluido com sucesso.");
      }catch (Exception e){
         log.error("[PacienteService] [deletePaciente] Não foi possível excluir Paciente", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível  excluir o paciente: " + id);
      }
   }


}
