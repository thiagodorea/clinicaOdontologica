package com.dh.clinicaodonto.repository;

import com.dh.clinicaodonto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   Optional<Usuario> findByUsername(String username);
}
