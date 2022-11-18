package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @PostMapping("login")
    public void login() {
    }

    @PostMapping("criar")
    @ResponseBody
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
    }

}
