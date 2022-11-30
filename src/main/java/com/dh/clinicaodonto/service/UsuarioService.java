package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

//@Service
public interface UsuarioService {
    ResponseEntity<String> login (UsuarioDto usuario);
    ResponseEntity<UsuarioDto> saveUsuario(Usuario usuario);


}
