package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {

   Optional<Dentista> findByMatricula(String matricula);
}
