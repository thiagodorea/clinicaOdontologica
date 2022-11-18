package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
