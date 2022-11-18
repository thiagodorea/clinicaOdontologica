package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Usuario;

public interface UsuarioService {
    void login ();
    Usuario saveUsuario(Usuario usuario);

    void resetPassword();
}
