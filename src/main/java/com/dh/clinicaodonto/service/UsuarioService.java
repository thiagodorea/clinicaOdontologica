package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//@Service
public interface UsuarioService {
    ResponseEntity<UsuarioDto> login (String username, String password);
    ResponseEntity<UsuarioDto> saveUsuario(Usuario usuario);

    void resetPassword();
}
