package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("login")
    public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto usuario) {
        return usuarioService.login(usuario);
    }


    @ResponseBody
    @PostMapping
    public ResponseEntity criar(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

}
