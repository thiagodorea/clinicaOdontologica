package com.dh.clinicaodonto.security;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

   @Autowired
   UsuarioRepository usuarioRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
      if(usuario.isEmpty())
         throw new UsernameNotFoundException("Usuario não encontrado");
      return usuario.get();
   }
}
