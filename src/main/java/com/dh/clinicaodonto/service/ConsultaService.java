package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
import com.dh.clinicaodonto.exception.InvalidRegistrationException;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultaService {

   ResponseEntity<List<ConsultaDto>> findAllConsultas() throws ResourceNotFoundException;
   ResponseEntity<List<ConsultaDto>> findConsultaByRg(String rg) throws ResourceNotFoundException;
   ResponseEntity<ConsultaDto> saveConsulta(ConsultaMarcacaoDto consultaMarcacaoDto) throws InvalidRegistrationException;
   ResponseEntity<ConsultaDto> updateConsultaByRg(ConsultaMarcacaoDto consultaMarcacaoDto) throws InvalidRegistrationException, ResourceNotFoundException;
   ResponseEntity<String> deleteConsulta(ConsultaMarcacaoDto consultaMarcacao) throws ResourceNotFoundException;

}
