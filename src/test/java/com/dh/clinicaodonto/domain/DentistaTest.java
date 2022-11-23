package com.dh.clinicaodonto.domain;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DentistaTest {

    @Test
    void criarDentista() {

        Dentista desntistaTeste = new Dentista();

        desntistaTeste.setId(Long.valueOf("1232"));
        desntistaTeste.setNome("Dentista");
        desntistaTeste.setSobrenome("Sobrenome");
        desntistaTeste.setMatricula("123");

        Assertions.assertEquals(desntistaTeste.getNome(), "Dentista");
        Assertions.assertEquals(desntistaTeste.getSobrenome(), "Sobrenome");
        Assertions.assertEquals(desntistaTeste.getMatricula(), "123");

    }

}