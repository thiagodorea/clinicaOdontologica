package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    ResponseEntity<String> login (String email, String password);
    ResponseEntity<UsuarioDto> saveUsuario(Usuario usuario);

    void resetPassword();
}
