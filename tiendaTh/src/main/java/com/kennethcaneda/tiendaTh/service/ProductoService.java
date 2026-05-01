package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Productos;

import java.util.List;

public interface ProductoService {
    List<Productos> listar();
    Productos crear(Productos producto);
    Productos actualizar(Integer id, Productos producto);
    Productos buscarPorId(Integer id);
    void eliminar(Integer id);
}
