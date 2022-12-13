package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil,Long> {
}
