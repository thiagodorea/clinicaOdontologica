package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.dto.UsuarioDto;
import com.dh.clinicaodonto.dto.UsuarioNovoDto;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import com.dh.clinicaodonto.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

   @Autowired
   private UsuarioServiceImpl usuarioService;
   @PostMapping()
   public ResponseEntity novoUsuario(@RequestBody @Valid UsuarioNovoDto usuarioNovoDto){
      return usuarioService.novoUsuario(usuarioNovoDto);
   }

   @GetMapping("/perfil/{username}")
   public ResponseEntity buscarPerfil(@PathVariable String username) throws ResourceNotFoundException {
      return usuarioService.buscarPerfil(username);
   }

}
