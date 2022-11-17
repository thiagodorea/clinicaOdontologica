package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;

import java.util.List;

public interface DentistaService {

    List<Dentista> findAllDenstistas();

    Dentista findDentistaById(long id);

    Dentista saveDentista(Dentista dentista);

    Dentista updateDentistaById(Dentista dentista);

    void deleteDentista(long id);
}
