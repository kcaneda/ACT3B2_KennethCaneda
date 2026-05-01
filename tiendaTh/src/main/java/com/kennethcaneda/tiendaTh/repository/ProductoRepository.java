package com.kennethcaneda.tiendaTh.repository;

import com.kennethcaneda.tiendaTh.entity.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Productos, Integer> {
}
