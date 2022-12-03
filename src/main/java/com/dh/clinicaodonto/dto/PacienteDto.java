package com.dh.clinicaodonto.dto;

import com.dh.clinicaodonto.domain.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDto {
    private String nome;
    private String sobrenome;
    private String rg;
    private LocalDate dataCadastro;
    private EnderecoDto endereco;

}
