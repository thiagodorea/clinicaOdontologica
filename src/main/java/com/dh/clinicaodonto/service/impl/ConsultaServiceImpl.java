package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Consulta;
import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
import com.dh.clinicaodonto.repository.ConsultaRepository;
import com.dh.clinicaodonto.service.ConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Log4j2
@Service
public class ConsultaServiceImpl implements ConsultaService {

   @Autowired
   ConsultaRepository consultaRepository;
   @Autowired
   @Lazy
   PacienteServiceImpl pacienteService;
   @Autowired
   DentistaServiceImpl dentistaService;

   ObjectMapper mapper = new ObjectMapper();

   final String CONSULTARETROATIVA = "ATENÇÃO: Não é possível agendar consulta retroativa.";

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
         return new ResponseEntity("Não localizamos as consultas do(a) paciente: "+rg,HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<List<ConsultaDto>>findConsultaByMatricula(String matricula) {
      log.info("[ConsultaService] [findConsultaByMatricula]");
      mapper.registerModule(new JavaTimeModule());
      try{
         List<Consulta> consultas = consultaRepository.findByDentistaMatricula(matricula);
         List<ConsultaDto> consultasDto = new ArrayList<>();
         for(Consulta consulta : consultas){
            consultasDto.add(mapper.convertValue(consulta,ConsultaDto.class));
         }
         return ResponseEntity.status(HttpStatus.OK).body(consultasDto);
      }catch (Exception e){
         return new ResponseEntity("Não localizamos as consultas do dentista: "+matricula,HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<ConsultaDto> saveConsulta(ConsultaMarcacaoDto consultaMarcacao) {
      log.info("[ConsultaService] [saveConsulta]");
      if(LocalDateTime.now().isAfter(consultaMarcacao.getDhConsulta()))
         return new ResponseEntity(CONSULTARETROATIVA,HttpStatus.BAD_REQUEST);
      Paciente paciente = new Paciente();
      Dentista dentista = new Dentista();
      mapper.registerModule(new JavaTimeModule());
      try{
         paciente = pacienteService.responsePacienteByRg(consultaMarcacao.getRgPaciente());
         dentista = dentistaService.responseDentistaByMatricula(consultaMarcacao.getMatriculaDentista());
         List<ConsultaDto> listaDeConsulta = findConsultaByRg(consultaMarcacao.getRgPaciente()).getBody();
         for(ConsultaDto consultaDto : listaDeConsulta){
            LocalDateTime dhConsultaDaLista = consultaDto.getDhConsulta();
            LocalDateTime dhEnviado = consultaMarcacao.getDhConsulta();
            if(dhConsultaDaLista.minusMinutes(30).isBefore(dhEnviado) && dhConsultaDaLista.plusMinutes(30).isAfter(dhEnviado) || consultaDto.getDhConsulta().isEqual(consultaMarcacao.getDhConsulta()))
               return new ResponseEntity("O Paciente já possui uma consulta neste horário, favor respeitar um intervalo de 30 minutos..",HttpStatus.BAD_REQUEST);
            List<Consulta> dentistaTemConsulta =  responseConsultaByDentistaAndDhConsulta(consultaMarcacao.getMatriculaDentista(),consultaMarcacao.getDhConsulta().minusMinutes(30),consultaMarcacao.getDhConsulta().plusMinutes(30));
            if(dentistaTemConsulta.size() > 0)
               return new ResponseEntity("O Dentista "+dentista.getMatricula()+ ", " + dentista.getNome()+" "+ dentista.getSobrenome()+" já possui uma consulta neste horário.",HttpStatus.BAD_REQUEST);
         }

         Consulta consulta = new Consulta();
         consulta.setConsultaId(listaDeConsulta.size() + 1);
         consulta.setDhConsulta(consultaMarcacao.getDhConsulta());
         consulta.setPaciente(paciente);
         consulta.setDentista(dentista);
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(consultaRepository.save(consulta), ConsultaDto.class));
      }catch (Exception e){
         log.error("[ConsultaService] [saveConsulta] Não foi possível agendar a consulta.");
         if(isNull(paciente.getRg()))
            return new ResponseEntity("Não foi possível agendar a sua consulta.\nSeu cadastro não foi encontrado.",HttpStatus.BAD_REQUEST);
         if(isNull(dentista.getMatricula()))
            return new ResponseEntity("Não foi possível agendar a sua consulta.\nO dentista informado não foi localizado.",HttpStatus.BAD_REQUEST);
         return new ResponseEntity("Não foi possível agendar a sua consulta, favor verificar os dados informado.",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity<ConsultaDto> updateConsultaByRg(ConsultaMarcacaoDto consultaMarcacao) {
      log.info("[ConsultaService] [updateConsultaByRg]");
      if(LocalDateTime.now().isAfter(consultaMarcacao.getDhConsulta()))
         return new ResponseEntity(CONSULTARETROATIVA,HttpStatus.BAD_REQUEST);
      Paciente paciente = new Paciente();
      Dentista dentista = new Dentista();
      Consulta consultaAtualizada = new Consulta();
      mapper.registerModule(new JavaTimeModule());
      try{
         List<ConsultaDto> listaDeConsulta = findConsultaByRg(consultaMarcacao.getRgPaciente()).getBody();
         for(ConsultaDto consultaDto : listaDeConsulta){
            LocalDateTime dhConsultaDaLista = consultaDto.getDhConsulta();
            LocalDateTime dhEnviado = consultaMarcacao.getDhConsulta();
            if(dhConsultaDaLista.minusMinutes(30).isBefore(dhEnviado) && dhConsultaDaLista.plusMinutes(30).isAfter(dhEnviado) || consultaDto.getDhConsulta().isEqual(consultaMarcacao.getDhConsulta()))
               return new ResponseEntity("O Paciente já possui uma consulta neste horário, favor respeitar um intervalo de 30 minutos.",HttpStatus.BAD_REQUEST);
            List<Consulta> dentistaTemConsulta =  responseConsultaByDentistaAndDhConsulta(consultaMarcacao.getMatriculaDentista(),consultaMarcacao.getDhConsulta().minusMinutes(30),consultaMarcacao.getDhConsulta().plusMinutes(30));
            if(dentistaTemConsulta.size() > 0)
               return new ResponseEntity("O Dentista "+dentista.getMatricula()+ ", " + dentista.getNome()+" "+ dentista.getSobrenome()+" já possui uma consulta neste horário.",HttpStatus.BAD_REQUEST);
         }
         Consulta consulta = responseConsultaByConsultaIdRg(consultaMarcacao.getConsultaId(),consultaMarcacao.getRgPaciente());
         dentista = dentistaService.responseDentistaByMatricula(consultaMarcacao.getMatriculaDentista());
         paciente = pacienteService.responsePacienteByRg(consultaMarcacao.getRgPaciente());
         consultaAtualizada.setId(consulta.getId());
         consultaAtualizada.setConsultaId(consultaMarcacao.getConsultaId());
         consultaAtualizada.setDhConsulta(consultaMarcacao.getDhConsulta());
         consultaAtualizada.setPaciente(paciente);
         consultaAtualizada.setDentista(dentista);
         return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(consultaRepository.save(consultaAtualizada),ConsultaDto.class));
      }catch (Exception e){
         log.error("[ConsultaService] [updateConsultaById] Erro ao atualizar a consulta", e);
         return new ResponseEntity("A consulta do paciente não foi encontrada.",HttpStatus.NOT_FOUND);
      }
   }

   @Override
   public ResponseEntity<String> deleteConsulta(ConsultaMarcacaoDto consultaMarcacao) {
      log.info("[ConsultaService] [deleteConsulta]");
      try {
         Consulta consulta = responseConsultaByConsultaIdRg(consultaMarcacao.getConsultaId(),consultaMarcacao.getRgPaciente());
         if(isNull(consulta))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não encontramos consulta do paciente: "+ consultaMarcacao.getRgPaciente());
         consultaRepository.deleteById(consulta.getId());
         return ResponseEntity.status(HttpStatus.OK).body("A consulta do(a) paciente " + consulta.getPaciente().getNome()+" "+ consulta.getPaciente().getSobrenome()+", no dia "+ consulta.getDhConsulta()+" foi excluida com sucesso.");
      }catch (Exception e){
         log.error("[ConsultaService] [deleteConsulta] Não foi possível excluir a consulta", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível  excluir a consulta do paciente: " + consultaMarcacao.getRgPaciente());
      }
   }

   public Consulta responseConsultaByConsultaIdRg(int consultaId, String rg){
      log.info("[ConsultaService] [responseConsultaByRg]");
      return consultaRepository.findByConsultaIdAndPacienteRg(consultaId,rg);
   }

   public List<Consulta> responseConsultaByDentistaAndDhConsulta(String matricula, LocalDateTime dataHoraDaConsultaIni, LocalDateTime dataHoraDaConsultaFim){
      log.info("[ConsultaService] [responseConsultaByDentistaAndDhConsulta]");
      return consultaRepository.findByDentistaMatriculaAndDhConsultaBetween(matricula,dataHoraDaConsultaIni,dataHoraDaConsultaFim);
   }

}
