package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.repository.UsuarioRepository;
import com.dh.clinicaodonto.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Log4j2
@Service
public class UsuarioServiceImpl implements UsuarioService {

//    @Autowired
//    private UsuarioRepository repository;
//    ObjectMapper mapper = new ObjectMapper();
//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    @Override
//    public ResponseEntity login(UsuarioDto usuariodto) {
//        mapper.registerModule(new JavaTimeModule());
//        log.info("[UsuarioService] [loginUsuario]");
//
//        try {
//            Optional<Usuario> user = repository.findByUsername(usuariodto.getUsername());
//            if(user.isEmpty()) {
//                return new ResponseEntity<>("usuario não cadastrado no sistema", HttpStatus.BAD_REQUEST);
//            }
//            if(!decodePassword(usuariodto.getPassword(),user.get().getPassword()) ) {
//                return new ResponseEntity<>("usuario ou senha inválido", HttpStatus.BAD_REQUEST);
//            }
////
//                return new ResponseEntity("TOKEN", HttpStatus.OK);
//        } catch (Exception err) {
//            return  new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    @Override
//    public ResponseEntity saveUsuario(Usuario usuario) {
//        log.info("[UsuarioService] [saveUsuario]");
//
//        try {
//            mapper.registerModule(new JavaTimeModule());
//            Optional alreadyExists = repository.findByUsername(usuario.getUsername());
//
//            if (alreadyExists.isEmpty()) {
//                usuario.setPassword(encoderPassaword(usuario.getPassword()));
//                mapper.convertValue(repository.save(usuario), UsuarioDto.class);
//                return ResponseEntity.status(HttpStatus.CREATED).body("Usuário " + usuario.getUsername()+" criado com sucesso");
//            }
//            log.error("[UsuarioService] [saveUsuario] " +usuario.getUsername() + " Usuário já cadastrado no sistema");
//            return new ResponseEntity("Usuário já cadastrado no sistema",HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            log.error("[UsuarioService] [saveUsuario] Não foi possível salvar o usuario");
//            return new ResponseEntity("Erro ao criar Usuário",HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    public String encoderPassaword(String password) {
//        return bCryptPasswordEncoder.encode(password);
//    }
//
//    public boolean decodePassword(String password, String  encodedPassword) {
//        return bCryptPasswordEncoder.matches(password, encodedPassword);
//    }
}
