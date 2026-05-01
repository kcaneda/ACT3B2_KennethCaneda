package com.kennethcaneda.tiendaTh.repository;

import com.kennethcaneda.tiendaTh.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
    boolean existsByDpiCliente(Long dpiCliente);
}
