package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.repository.DentistaRepository;
import com.dh.clinicaodonto.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DentistaServiceImpl implements DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    @Override
    public Dentista criar() {
        return null;
    }

    @Override
    public Dentista editar() {
        return null;
    }

    @Override
    public Dentista exlcuir() {
        return null; 
    }

    @Override
    public List<Paciente> listarDentistas() {
        return dentistaRepository.findAll();
    }

    @Override
    public Dentista buscarDentista() {
        return null;
    }
}
