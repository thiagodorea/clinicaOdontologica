package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Dentista;
import com.dh.clinicaodonto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    Dentista findByMatricula(String matricula);
    Dentista deleteByMatricula(String matricula);

}
