package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Consulta;
import com.dh.clinicaodonto.dto.ConsultaDto;
import com.dh.clinicaodonto.dto.ConsultaMarcacaoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultaService {

   List<ConsultaDto> findAllConsultas();
   ResponseEntity<ConsultaDto> findConsultaById(Long id);
   ResponseEntity<List<ConsultaDto>> findConsultaByRg(String rg);
   ResponseEntity<ConsultaDto> saveConsulta(ConsultaMarcacaoDto consultaMarcacaoDto);
   ResponseEntity<ConsultaDto> updateConsultaByRg(ConsultaMarcacaoDto consultaMarcacaoDto);
   ResponseEntity<String> deleteConsulta(Long id);

   ResponseEntity<List<Consulta>> findConsultaByMatricula(String matricula);

}
