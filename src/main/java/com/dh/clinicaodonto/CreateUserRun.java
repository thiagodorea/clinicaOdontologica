package com.dh.clinicaodonto;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CreateUserRun implements ApplicationRunner {

   @Autowired
   UsuarioRepository usuarioRepository;

   @Override
   public void run(ApplicationArguments args) throws Exception {
      BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

      Usuario usuario =new Usuario();
      usuario.setUsername("UserMaster");
      usuario.setPassword(enc.encode("123456"));

      if(usuarioRepository.findAll().size() == 0)
         usuarioRepository.save(usuario);
   }
}
