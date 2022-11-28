package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByRg(String rg);
}
