package com.dh.clinicaodonto.security;

import com.dh.clinicaodonto.domain.Usuario;
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
      Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(this.expiracao));

      return Jwts.builder()
              .setIssuer("API ClinicaOdonto")
              .setSubject(usuarioLogado.getUsername())
              .setIssuedAt(hoje)
              .setExpiration(dataExpiracao)
              .signWith(SignatureAlgorithm.HS256, this.secret)
              .compact();
   }
}
