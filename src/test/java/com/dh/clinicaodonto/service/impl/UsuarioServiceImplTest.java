package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsuarioServiceImplTest {
    @Autowired
    private UsuarioService service;

    UsuarioDto usuarioRetorno = new UsuarioDto();

    @Test
    void saveUsuario() {
//        Usuario user =  new Usuario(1L,"Teste", "123", true);
//        usuarioRetorno =  service.saveUsuario(user).getBody();
//        assertTrue(user.getId() > 0);
//        assertEquals(user.getUsername(), "Teste");

    }
}