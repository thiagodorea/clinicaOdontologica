package com.dh.clinicaodonto.controller;

import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.service.impl.DentistaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class DentistaController {
    @Autowired
    DentistaServiceImpl dentistaService;

    @GetMapping
    public List<Paciente> listarDentistas() { return dentistaService.listarDentistas();}
}
