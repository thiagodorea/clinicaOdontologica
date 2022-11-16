package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.repository.DentistaRepository;
import com.dh.clinicaodonto.service.DentistaService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class DentistaServiceImpl implements DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;



    @Override
    public List<Dentista> findAllDenstistas() {
        return dentistaRepository.findAll();
    }



    @Override
    public Dentista findDentistaById(long id) {
        log.info("[DentistaService] [findDentistaById]");
        return dentistaRepository.findById(id).orElseThrow( () -> {
            log.error("[DentistaService] [findDentistaById] Dentista não encontrado");
            throw new ObjectNotFoundException(" Dentista "+ id + " não encontrado.", Dentista.class.getName());
        });
    }

    @Override
    public Dentista saveDentista() {
        return null;
    }

    @Override
    public Dentista updateDentistaById(Dentista dentista) {
        return null;
    }

    @Override
    public void deleteDentista(Dentista dentista) {

    }
}
