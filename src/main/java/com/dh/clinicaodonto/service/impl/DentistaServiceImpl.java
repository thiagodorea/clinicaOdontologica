package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.repository.DentistaRepository;
import com.dh.clinicaodonto.service.DentistaService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

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
    public Dentista saveDentista(Dentista dentista) {
        log.info("[DentistaService] [saveDentista]");
        return dentistaRepository.save(dentista);
    }

    @Override
    public Dentista updateDentistaById(Dentista dentista) {
        log.info("[DentistaService] [updateDentistaById]");
        try{
            nonNull(findDentistaById(dentista.getId()).getId());
            return dentistaRepository.save(dentista);
        }catch (Exception e){
            log.error("[DentistaService] [updateDentistaById] Dentista não foi encontrado", e);
            throw new RuntimeException("[DentistaService] [updateDentistaById] Dentista não foi encontrado");
        }
    }

    @Override
    public void deleteDentista(long id) {
        log.info("[DentistaService] [deleteDentista]");
        try {
            nonNull(findDentistaById(id));
            dentistaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            log.error("[DentistaService] [deleteDentista] Error ao excluir Dentista", e);
            throw new DataIntegrityViolationException("[DentistaService] [deleteDentista] Error ao excluir o Dentista: " + id );
        }
    }
}
