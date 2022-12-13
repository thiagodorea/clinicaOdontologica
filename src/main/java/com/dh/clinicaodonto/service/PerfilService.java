package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.dto.PerfilDto;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PerfilService {
   ResponseEntity<List<PerfilDto>> findAllPerfis() throws ResourceNotFoundException;
}
