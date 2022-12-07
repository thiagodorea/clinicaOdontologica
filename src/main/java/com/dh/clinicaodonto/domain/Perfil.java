package com.dh.clinicaodonto.domain;

import org.springframework.security.core.GrantedAuthority;

public class Perfil implements GrantedAuthority {
   private Long id;
   private String descricao;

   @Override
   public String getAuthority() {
      return null;
   }
}
