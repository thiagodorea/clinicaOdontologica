package com.dh.clinicaodonto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
   @NotBlank
   @Length(min = 5)
   private String username;
   @NotBlank
   @Length(min = 5)
   private String password;

   public UsernamePasswordAuthenticationToken converter(){
      return new UsernamePasswordAuthenticationToken(this.username, this.password);
   }
}
