package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.UsuarioDto;
import com.dh.clinicaodonto.service.DentistaService;
import com.dh.clinicaodonto.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
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