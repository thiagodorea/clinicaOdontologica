package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioDto;
import com.dh.clinicaodonto.repository.UsuarioRepository;
import com.dh.clinicaodonto.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired

    private UsuarioRepository repository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ResponseEntity<UsuarioDto> login(String username, String password) {
        mapper.registerModule(new JavaTimeModule());
        log.info("[UsuarioService] [loginUsuario]");
        return  ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(repository.findByUsername(username),UsuarioDto.class));
    }

    @Override
    public ResponseEntity<UsuarioDto> saveUsuario(Usuario usuario) {
        log.info("[UsuarioService] [saveUsuario]");
        try {
            mapper.registerModule(new JavaTimeModule());
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(repository.save(usuario), UsuarioDto.class));
        } catch (Exception e) {
            log.error("[UsuarioService] [saveUsuario] Não foi possível salvar o usuario");
            return new ResponseEntity("Erro ao criar Usuário",HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public void resetPassword() {

    }
}
