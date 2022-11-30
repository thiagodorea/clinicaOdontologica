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

import java.util.Optional;


@Log4j2
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ResponseEntity login(UsuarioDto usuario) {
        mapper.registerModule(new JavaTimeModule());
        log.info("[UsuarioService] [loginUsuario]");

        try {
            Optional<Usuario> user = repository.findByUsername(usuario.getUsername());
            if(user.isEmpty()) {
                return new ResponseEntity<>("usuario ou senha inválido", HttpStatus.BAD_REQUEST);
            }
            if(!usuario.getPassword().equals(user.get().getPassword()) ) {
                return new ResponseEntity<>("usuario ou senha inválido", HttpStatus.BAD_REQUEST);
            }
//
                return new ResponseEntity("TOKEN", HttpStatus.OK);
        } catch (Exception err) {
            return  new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }

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


}
