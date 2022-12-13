package com.dh.clinicaodonto.service.impl;

import com.dh.clinicaodonto.dto.DentistaDto;
import com.dh.clinicaodonto.dto.DentistaResponseDto;
import com.dh.clinicaodonto.dto.PerfilDto;
import com.dh.clinicaodonto.dto.UsuarioNovoDto;
import com.dh.clinicaodonto.service.DentistaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class DentistaServiceImplTest {
    @Autowired
    private DentistaService service;

    @Test
    void findAllDenstistas() {
        assertTrue(service.findAllDenstistas().size() > 0);
    }

    @Test
    void findByMatricula() {
        DentistaResponseDto dentistaResponseDtoDto = service.findByMatricula("0000001").getBody();
        assertEquals("Marcela",dentistaResponseDtoDto.getNome());
    }
    @Test
    void saveDentista() {
        List<PerfilDto> perfisDto = Arrays.asList(new PerfilDto(2L,null));
        DentistaDto dentistaDto = new DentistaDto( "DentistaTest", "Teste", "0000",new UsuarioNovoDto("0000","1234",perfisDto));
        DentistaResponseDto dentistaSalvo = service.saveDentista(dentistaDto).getBody();
        assertTrue(dentistaSalvo.getMatricula().equals(dentistaDto.getMatricula()));
        assertEquals(dentistaSalvo.getNome(), "DentistaTest");
    }

    @Test
    void updateDentistaByMatricula() {
        List<PerfilDto> perfisDto = Arrays.asList(new PerfilDto(2L,null));
        DentistaDto dentista = new DentistaDto( "Marcela","TesteD","0000003",new UsuarioNovoDto("000","1234",perfisDto));
        assertEquals(200,service.updateDentistaByMatricula(dentista).getStatusCode().value());
    }

    @Test
    void deleteDentista() {
        List<PerfilDto> perfisDto = Arrays.asList(new PerfilDto(2L,null));
        DentistaDto dentistaDto = new DentistaDto( "DentistaTest", "Teste", "000",new UsuarioNovoDto("000","1234",perfisDto));
        service.saveDentista(dentistaDto);
        assertEquals(200,service.deleteDentista("000").getStatusCode().value());
        assertEquals(400,service.deleteDentista("0000000").getStatusCode().value());
    }


}