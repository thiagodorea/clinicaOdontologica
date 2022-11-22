package com.dh.clinicaodonto.dto;

import com.dh.clinicaodonto.domain.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)



public class PacienteDto {
    private String nome;
    private String sobrenome;
    private String matricula;
    private UsuarioDto usuario;
    private Endereco endereco;
    private LocalDate dataCadastro;
}
