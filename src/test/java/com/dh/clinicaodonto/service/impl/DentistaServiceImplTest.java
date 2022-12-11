package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.PacienteDto;
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
    void findByMatricula() {
        DentistaDto dentistaDto = service.findByMatricula("0000003").getBody();
        assertEquals("Marcela",dentistaDto.getNome());
    }
    @Test
    void saveDentista() {
        DentistaDto dentistaDto = new DentistaDto( "DentistaTest", "Teste", "000");
        DentistaDto dentistaSalvo = service.saveDentista(dentistaDto).getBody();
        assertTrue(dentistaSalvo.getMatricula().equals(dentistaRetorno.getMatricula()));
        assertEquals(dentistaSalvo.getNome(), "DentistaTest");
    }

    @Test
    void updateDentistaByMatricula() {
        DentistaDto dentista = new DentistaDto( "Marcela","TesteD","0000003");
        assertEquals(200,service.updateDentistaByMatricula(dentista).getStatusCode().value());
    }

    @Test
    void deleteDentista() {
        DentistaDto dentistaDto = new DentistaDto( "DentistaTest", "Teste", "000");
        service.saveDentista(dentistaDto);
        assertEquals(200,service.deleteDentista("000").getStatusCode().value());
        assertEquals(400,service.deleteDentista("0000000").getStatusCode().value());
    }


}