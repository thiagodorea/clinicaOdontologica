package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioNovoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
   ResponseEntity<UsuarioNovoDto> novoUsuario(UsuarioNovoDto usuarioNovoDto);
}
