package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Perfil;
import com.dh.clinicaodonto.dto.PerfilDto;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import com.dh.clinicaodonto.repository.PerfilRepository;
import com.dh.clinicaodonto.service.PerfilService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class PerfilServiceImpl implements PerfilService {

   @Autowired
   private PerfilRepository perfilRepository;

   ObjectMapper mapper = new ObjectMapper();
   @Override
   public ResponseEntity<List<PerfilDto>> findAllPerfis() throws ResourceNotFoundException {
      log.info("[PerfilService] [findAllPerfis]");
      List<Perfil> perfis = perfilRepository.findAll();
      List<PerfilDto> perfisDto = new ArrayList<>();
      for(Perfil perfil : perfis) {
         perfisDto.add(mapper.convertValue(perfil,PerfilDto.class));
      }
      if(perfis.isEmpty())
         throw new ResourceNotFoundException("Não localizamos nenhum perfil.");
      return ResponseEntity.status(HttpStatus.OK).body(perfisDto);
   }
}
