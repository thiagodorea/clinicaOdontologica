package com.dh.clinicaodonto.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteResponseDto {
    @NotBlank(message = "Nome do paciente é obrigatório.")
    @Size(min=2, max=50, message = "O nome precisa ser maior que 2 e nenor que 50 caracteres.")
    private String nome;
    @NotBlank(message = "Sobrenome do paciente é obrigatório.")
    @Size(min=2, max=250, message = "O nome precisa ser maior que 2 e nenor que 250 caracteres.")
    private String sobrenome;
    @NotBlank(message = "RG do paciente é obrigatório.")
    @Size(min=9, max=10, message = "Rg precisa ter 10 caracteres.")
    private String rg;
    private LocalDate dataCadastro;
    private EnderecoDto endereco;

}
