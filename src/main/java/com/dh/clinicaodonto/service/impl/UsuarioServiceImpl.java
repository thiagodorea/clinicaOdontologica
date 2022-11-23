package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioDto;
import com.dh.clinicaodonto.repository.UsuarioRepository;
import com.dh.clinicaodonto.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ResponseEntity<String> login(String email, String password) {
        mapper.registerModule(new JavaTimeModule());
        return  ResponseEntity.status(HttpStatus.OK).body(email);
    }

    @Override
    public ResponseEntity<UsuarioDto> saveUsuario(Usuario usuario) {
        try {
            mapper.registerModule(new JavaTimeModule());
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(repository.save(usuario), UsuarioDto.class));
        } catch (Exception e) {
            return new ResponseEntity("Erro ao criar Usuário",HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public void resetPassword() {

    }
}
