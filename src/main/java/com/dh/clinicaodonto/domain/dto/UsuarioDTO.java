package com.dh.clinicaodonto.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private long id;
    private String usuario;
    private String password;
    private Boolean isAdmin;
}
