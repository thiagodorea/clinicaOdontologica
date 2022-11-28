package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Usuario;
import com.dh.clinicaodonto.dto.UsuarioDto;
import com.dh.clinicaodonto.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @PostMapping("login")
    public ResponseEntity<UsuarioDto> login(String username, String password) {
        return usuarioService.login(username,password);
    }

    @PostMapping("criar")
    @ResponseBody
    public ResponseEntity<UsuarioDto> criar(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

}
