package com.dh.clinicaodonto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioNovoDto {
   @NotBlank
   @Length(min = 5, max = 20)
   private String username;
   @NotBlank
   @Length(min = 5, max = 90)
   private String password;
   private List<PerfilDto> perfis;

   public String getUsername() {
      return username.toLowerCase();
   }

   public void setUsername(String username) {
      this.username = username.toLowerCase();
   }
}
