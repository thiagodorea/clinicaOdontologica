package com.dh.clinicaodonto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerfilDto {
   @NotBlank
   private Long id;
   private String descricao;
}
