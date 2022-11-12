package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Paciente, Long> {
}
