package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioNovoDto;
import com.dh.clinicaodonto.dto.UsuarioPerfilDto;
import com.dh.clinicaodonto.exception.ResourceNotFoundException;
import com.dh.clinicaodonto.repository.UsuarioRepository;
import com.dh.clinicaodonto.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UsuarioServiceImpl implements UsuarioService {

   @Autowired
   private UsuarioRepository usuarioRepository;
   ObjectMapper mapper = new ObjectMapper();

   @Override
   public ResponseEntity<UsuarioNovoDto> novoUsuario(UsuarioNovoDto usuarioNovoDto) {
      log.info("[UsuarioServiceImpl] [novoUsuario]");
      BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
      try {
         Usuario usuario = mapper.convertValue(usuarioNovoDto,Usuario.class);
         usuario.setPassword(enc.encode(usuarioNovoDto.getPassword()));
         return ResponseEntity.status(HttpStatus.CREATED).body(mapper.convertValue(usuarioRepository.save(usuario),UsuarioNovoDto.class));
      }catch (Exception e){
         log.error("[UsuarioServiceImpl] [novoUsuario] Não foi possível criar novo usuario.");
         return new ResponseEntity("Não foi possível criar novo usuario ",HttpStatus.BAD_REQUEST);
      }
   }

   @Override
   public ResponseEntity buscarPerfil(String username) {
      System.out.println(username);
      Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
      if(usuario.isEmpty())
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário não encontrado");
      return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(usuario.get(), UsuarioPerfilDto.class));
   }
}
