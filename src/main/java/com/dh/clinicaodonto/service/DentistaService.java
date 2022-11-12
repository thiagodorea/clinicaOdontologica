package com.dh.clinicaodonto.service;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Paciente;

import java.util.List;

public interface DentistaService {
    Dentista criar();
    Dentista editar();
    Dentista exlcuir();
    List<Paciente> listarDentistas();
    Dentista buscarDentista();

}
