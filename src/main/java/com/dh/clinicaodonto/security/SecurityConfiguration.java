package com.dh.clinicaodonto.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private Environment env;


//   Aqui cria a parte das pessissões de acesso
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
         http.headers().frameOptions().disable();
      }
      http.cors().and().csrf().disable();
      http.authorizeRequests()
//              permite acesso ao metodo get da rota informada
              .antMatchers("/h2-console/**").permitAll()
//              bloqueia o acesso a qualquer outra rota não informado a cima
              .anyRequest().authenticated();
   }

//   Aqui cuidamos da parte de autenticação de acesso
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {

   }

   @Bean
   CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
      configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
   }

}
