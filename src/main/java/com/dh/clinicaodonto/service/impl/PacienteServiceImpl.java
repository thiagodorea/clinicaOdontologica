package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.repository.PacienteRepository;
import com.dh.clinicaodonto.service.PacienteService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PacienteServiceImpl implements PacienteService {

   @Autowired
   private PacienteRepository pacienteRepository;

   @Override
   public List<Paciente> findAllPacientes() {
      log.info("[PacienteService] [findAllPacientes]");
      return pacienteRepository.findAll();
   }

   @Override
   public Paciente findPacienteById(Long id) {
      log.info("[PacienteService] [findPacienteById]");
      return pacienteRepository.findById(id).orElseThrow(() -> {
         log.error("[PacienteService] [findPacienteById] Paciente não encontrado");
         throw new ObjectNotFoundException(" Paciente " + id + " não encontrado.", Paciente.class.getName());
      });
   }

   @Override
   public Paciente savePaciente(Paciente paciente) {
      log.info("[PacienteService] [savePaciente]");
      return pacienteRepository.save(paciente);
   }

   @Override
   public Paciente updatePacienteById(Paciente paciente) {
      log.info("[PacienteService] [updatePacienteById]");

      return pacienteRepository.save(paciente);
   }

   @Override
   public void deletePaciente(Long id) {
      log.info("[PacienteService] [deletePaciente]");
      findPacienteById(id);
      try {
         pacienteRepository.deleteById(id);
      }catch (DataIntegrityViolationException e){
         log.error("Error ao Deletar Paciente", e);
         throw new DataIntegrityViolationException("[PacienteService] [deletePaciente] Não foi possivel excluir o Paciente: " + id );
      }
   }


}
