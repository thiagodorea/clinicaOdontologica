package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Endereco;
import com.dh.clinicaodonto.domain.Paciente;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.service.DentistaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DentistaServiceImplTest {
    @Autowired
    private DentistaService service;

    DentistaDto dentistaRetorno = new DentistaDto();

    @Test
    void findAllDenstistas() {
        assertTrue(service.findAllDenstistas().size() > 0);
    }

    @Test
    void findDentistaById() {
        DentistaDto dentista = service.findDentistaById(1L).getBody();
        System.out.println(dentista.getNome());
        assertEquals("Marcela",dentista.getNome());
    }

    @Test
    void saveDentista() {
        Dentista dentista = new Dentista(1L, "DentistaTest", "Teste", "000");
        dentistaRetorno = service.saveDentista(dentista).getBody();
        assertTrue(dentista.getId() > 0);
        assertEquals(dentista.getNome(), "DentistaTest");
    }

    @Test
    void updateDentistaById() {
        Dentista dentista1 = new Dentista( 2L,"Teste","Teste","000");
        assertEquals(200,service.updateDentistaById(dentista1).getStatusCode().value());
    }

    @Test
    void deleteDentista() {
        assertEquals(200,service.deleteDentista(1l).getStatusCode().value());
        assertEquals(400,service.deleteDentista(0l).getStatusCode().value());
    }
}