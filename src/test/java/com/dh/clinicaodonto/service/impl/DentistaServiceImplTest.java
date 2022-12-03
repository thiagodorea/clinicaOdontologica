package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.service.DentistaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class DentistaServiceImplTest {
    @Autowired
    private DentistaService service;

    DentistaDto dentistaRetorno = new DentistaDto("DTeste", "DTeste", "000");

    @Test
    void findAllDenstistas() {
        assertTrue(service.findAllDenstistas().size() > 0);
    }

    @Test
    void findDentistaById() {
        DentistaDto dentista = service.findByMatricula("000000").getBody();
        assertEquals("Pedro",dentista.getNome());
    }

    @Test
    void saveDentista() {
        DentistaDto dentista = new DentistaDto( "DentistaTest", "Teste", "000");
        assertTrue(dentista.getMatricula().equals(dentistaRetorno.getMatricula()));
        assertEquals(dentista.getNome(), "DentistaTest");
    }

    @Test
    void updateDentistaById() {
        DentistaDto dentista1 = new DentistaDto( "TesteD","TesteD","000000");
        assertEquals(200,service.updateDentistaByMatricula(dentista1).getStatusCode().value());
    }

    @Test
    void deleteDentista() {
        assertEquals(200,service.deleteDentista("000000").getStatusCode().value());
        assertEquals(400,service.deleteDentista("000000").getStatusCode().value());
    }
}