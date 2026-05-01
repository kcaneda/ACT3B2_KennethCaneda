package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Ventas;

import java.util.List;

public interface VentaService {
    List<Ventas> listar();
    Ventas crear(Ventas venta);
    Ventas actualizar(Integer id, Ventas venta);
    Ventas buscarPorId(Integer id);
    void eliminar(Integer id);
}
