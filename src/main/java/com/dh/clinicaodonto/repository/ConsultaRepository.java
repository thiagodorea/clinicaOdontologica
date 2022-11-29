package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
   List<Consulta> findByPacienteRg(String rg);
   List<Consulta> findByDentistaMatricula(String matricula);
}
