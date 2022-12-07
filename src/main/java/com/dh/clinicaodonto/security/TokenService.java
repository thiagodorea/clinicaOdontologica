package com.dh.clinicaodonto.security;

import com.dh.clinicaodonto.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;



@Service
public class TokenService {

   @Value("${clinica.jwt.expiration}")
   private String expiracao;

   @Value("${clinica.jwt.secret}")
   private String secret;

   public String gerarToken(Authentication authentication) {
      Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
      Date hoje = new Date();
      Date dateExpiration = new Date(hoje.getTime() + Long.parseLong(this.expiracao));
      String token = Jwts.builder()
              //Setar a origem do token
              .setIssuer("Api Clinica Odonto")
              //Seta um valor unico para o token no caso é o username
              .setSubject(usuarioLogado.getUsername())
              //Seta a data de criação do token
              .setIssuedAt(hoje)
              //Seta a data de expiração do token
              .setExpiration(dateExpiration)
              //Setamos o metodo de criptografia e a palavra secreta
              .signWith(SignatureAlgorithm.HS256, this.secret)
              .compact();
      return token;
   }

   public boolean verificaToken(String token) {
      try {
         Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
         return true;
      }catch (Exception e) {
         return false;}
   }

   public String getUsernameUsuario(String token) {
      Claims claims =  Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
      String username = claims.getSubject();
      return username;
   }
}
