package com.dh.clinicaodonto.security;

import com.dh.clinicaodonto.dto.TokenDto;
import com.dh.clinicaodonto.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   TokenService tokenService;

   @PostMapping
   public ResponseEntity autenticar(@RequestBody @Valid UsuarioDto usuarioDto){
      try{
         UsernamePasswordAuthenticationToken loginUsuario = usuarioDto.converter();
         Authentication authentication = authenticationManager.authenticate(loginUsuario);
         String token = tokenService.gerarToken(authentication);
         TokenDto tokenDto = new TokenDto();
         tokenDto.setToken(token);
         tokenDto.setTipo("Bearer");
         return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
      }catch(AuthenticationException e){
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário ou senha inválido.");
      }
   }
}
