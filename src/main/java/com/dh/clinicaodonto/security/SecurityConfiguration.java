package com.dh.clinicaodonto.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private Environment env;

   @Autowired
   AutenticacaoService autenticacaoService;

   @Autowired
   AutenticacaoViaTokenFilter autenticacaoViaTokenFilter;

   @Bean
   @Override
   protected AuthenticationManager authenticationManager() throws Exception {
      return super.authenticationManager();
   }


//   Aqui cria a parte das pessissões de acesso
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
         http.headers().frameOptions().disable();
      }
      http.cors().and().csrf().disable()
              .authorizeRequests()
//              permite acesso ao metodo get da rota informada
              .antMatchers("/h2-console/**").permitAll()
              .antMatchers("/auth","v3/api-docs/**","/swagger-ui/**").permitAll()
              .antMatchers("/usuario/**").permitAll()
              .antMatchers(HttpMethod.POST,"/dentistas").permitAll()
              .antMatchers(HttpMethod.POST,"/pacientes").permitAll()
              .antMatchers("/perfis/**").hasAnyAuthority("Administrador")
              .antMatchers("/dentistas/**").hasAnyAuthority("Administrador")
              .antMatchers("/pacientes/**").hasAnyAuthority("Administrador")
              .antMatchers(HttpMethod.GET,"/usuario/perfil/**").hasAnyAuthority("Administrador")
//              bloqueia o acesso a qualquer outra rota não informado a cima
              .anyRequest().authenticated()
              .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and().addFilterBefore(autenticacaoViaTokenFilter, UsernamePasswordAuthenticationFilter.class);
   }

//   Aqui cuidamos da parte de autenticação de acesso
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(autenticacaoService)
              .passwordEncoder(new BCryptPasswordEncoder());
   }

//   Liberar os cors
   @Bean
   CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
      configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
      final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
   }

}
