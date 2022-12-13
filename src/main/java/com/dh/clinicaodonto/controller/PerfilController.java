package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.dto.PerfilDto;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import com.dh.clinicaodonto.service.impl.PerfilServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("perfis")
public class PerfilController {

   @Autowired
   private PerfilServiceImpl perfilService;

   @GetMapping()
   public ResponseEntity<List<PerfilDto>> findAllPerfis() throws ResourceNotFoundException {
      return perfilService.findAllPerfis();
   }
}
