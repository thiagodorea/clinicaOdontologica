package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Consulta;
import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.PacienteDto;
import com.dh.clinicaodonto.repository.ConsultaRepository;
import com.dh.clinicaodonto.service.ConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Log4j2
@Service
public class ConsultaServiceImpl implements ConsultaService {

   @Autowired
   ConsultaRepository consultaRepository;
   @Autowired
   PacienteServiceImpl pacienteService;
   @Autowired
   DentistaServiceImpl dentistaService;

   ObjectMapper mapper = new ObjectMapper();
   @Override
   public List<ConsultaDto> findAllConsultas() {
      log.info("[ConsultaService] [findAllConsultas]");
      List<Consulta> consultas = consultaRepository.findAll();
      List<ConsultaDto> consultasDto = new ArrayList<>();
      mapper.registerModule(new JavaTimeModule());
      for(Consulta consulta: consultas) {
         consultasDto.add(mapper.convertValue(consulta, ConsultaDto.class));
      }
      return consultasDto;
   }

   @Override
   public ResponseEntity<ConsultaDto> findConsultaById(Long id) {
      log.info("[ConsultaService] [findConsultaById]");
      mapper.registerModule(new JavaTimeModule());
      try{
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(consultaRepository.findById(id).get(),ConsultaDto.class));
      }catch (Exception e){
         log.error("Consulta " +id +" não foi localizada.", e);
         return new ResponseEntity("Consulta não localizada",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<List<ConsultaDto>> findConsultaByRg(String rg) {
      log.info("[ConsultaService] [findConsultaByRg]");
      mapper.registerModule(new JavaTimeModule());
      try{
         List<Consulta> consultas = consultaRepository.findByPacienteRg(rg);
         List<ConsultaDto> consultasDto = new ArrayList<>();
         for(Consulta consulta : consultas){
            consultasDto.add(mapper.convertValue(consulta,ConsultaDto.class));
         }
         return ResponseEntity.status(HttpStatus.OK).body(consultasDto);
      }catch (Exception e){
         return new ResponseEntity("Consulta não localizada",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<ConsultaDto> saveConsulta(ConsultaMarcacaoDto consultaMarcacao) {
      log.info("[ConsultaService] [saveConsulta]");
      PacienteDto pacienteDto = new PacienteDto();
      DentistaDto dentistaDto = new DentistaDto();
      try{
         pacienteDto = pacienteService.findByRg(consultaMarcacao.getRgPaciente()).getBody();
         dentistaDto = dentistaService.findByMatricula(consultaMarcacao.getMatriculaDentista()).getBody();
         mapper.registerModule(new JavaTimeModule());
         Consulta consulta = new Consulta();
         consulta.setDhConsulta(consultaMarcacao.getDhConsulta());
         consulta.setPaciente(mapper.convertValue(pacienteDto, Paciente.class));
         consulta.setDentista(mapper.convertValue(dentistaDto, Dentista.class));
         ConsultaDto consultaDto = mapper.convertValue(consulta, ConsultaDto.class);
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(consultaRepository.save(consulta), ConsultaDto.class));
      }catch (Exception e){
         log.error("[ConsultaService] [saveConsulta] Não foi possível agendar a consulta.");
         if(isNull(pacienteDto.getRg()))
            return new ResponseEntity("Não foi possível agendar a sua consulta.\nSeu cadastro não foi encontrado.",HttpStatus.BAD_REQUEST);
         if(isNull(dentistaDto.getMatricula()))
            return new ResponseEntity("Não foi possível agendar a sua consulta.\nO dentista informado não foi localizado.",HttpStatus.BAD_REQUEST);
         return new ResponseEntity("Não foi possível agendar a sua consulta, favor verificar os dados informado.",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<ConsultaDto> updateConsultaByRg(ConsultaMarcacaoDto consultaMarcacao) {
      log.info("[ConsultaService] [updateConsultaById]");
      PacienteDto pacienteDto = new PacienteDto();
      DentistaDto dentistaDto = new DentistaDto();
      try{
         mapper.registerModule(new JavaTimeModule());
         ConsultaDto consultaDto = findConsultaById(consultaMarcacao.getId()).getBody();
         pacienteDto = pacienteService.findByRg(consultaMarcacao.getRgPaciente()).getBody();
         dentistaDto = dentistaService.findByMatricula(consultaMarcacao.getMatriculaDentista()).getBody();
         Consulta consulta = new Consulta();
         consulta.setId(consultaMarcacao.getId());
         consulta.setDhConsulta(consultaMarcacao.getDhConsulta());
         consulta.setPaciente(mapper.convertValue(pacienteDto, Paciente.class));
         consulta.setDentista(mapper.convertValue(dentistaDto, Dentista.class));
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(consultaRepository.save(consulta), ConsultaDto.class));
      }catch (Exception e){
         log.error("[ConsultaService] [updateConsultaById] Erro ao atualizar a consulta", e);
         return new ResponseEntity("A consulta do paciente não foi encontrada.",HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public ResponseEntity<String> deleteConsulta(Long id) {
      log.info("[ConsultaService] [deleteConsulta]");
      try {
         consultaRepository.deleteById(id);
         return ResponseEntity.status(HttpStatus.OK).body("Consulta " + id +" excluida com sucesso.");
      }catch (Exception e){
         log.error("[ConsultaService] [deleteConsulta] Não foi possível excluir a consulta", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível  excluir a consulta: " + id);
      }
   }
}
