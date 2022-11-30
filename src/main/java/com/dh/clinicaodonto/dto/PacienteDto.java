package com.dh.clinicaodonto.dto;

import com.dh.clinicaodonto.domain.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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
    public String getNomeSobrenome() {
        return this.nome+ " " +this.sobrenome;
    }
}
