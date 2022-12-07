package com.dh.clinicaodonto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaMarcacaoDto {
   private int consultaId;
   @NotBlank(message = "Data e Hora é obrigatório.")
   private LocalDateTime dhConsulta;
   @NotBlank(message = "RG do paciente é obrigatório.")
   private String rgPaciente;
   @NotBlank(message="Matricula do dentista é obrigatório.")
   private String  matriculaDentista;
}