package com.dh.clinicaodonto.security;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

   @Autowired
   TokenService tokenService;

   @Autowired
   UsuarioRepository usuarioRepository;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String token = recuperarToken(request);
      boolean tokenValido = tokenService.verificaToken(token);
      if(tokenValido)
         autenticarUsuario(token);
      filterChain.doFilter(request, response);
   }

   private void autenticarUsuario(String token) {
      String username = tokenService.getUsernameUsuario(token);
      Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
      UsernamePasswordAuthenticationToken autenticationToken = new UsernamePasswordAuthenticationToken(usuario.get(),null, usuario.get().getAuthorities() );
      SecurityContextHolder.getContext().setAuthentication(autenticationToken);
   }

   private String recuperarToken(HttpServletRequest request) {
      String getToken = request.getHeader("Authorization");
      if(getToken == null || getToken.isEmpty() || !getToken.startsWith("Bearer ")) {
         return null;
      }
      return  getToken.substring(7, getToken.length());
   }
}
