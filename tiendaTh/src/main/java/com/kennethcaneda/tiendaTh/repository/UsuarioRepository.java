package com.kennethcaneda.tiendaTh.repository;

import com.kennethcaneda.tiendaTh.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Optional<Usuarios> findByUsername(String username);
    Optional<Usuarios> findByEmail(String email);
}