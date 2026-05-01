package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.DetallesVenta;

import java.util.List;

public interface DetallesVentaService {
    List<DetallesVenta> listar();
    DetallesVenta crear(DetallesVenta detallesVenta);
    DetallesVenta actualizar(Integer id, DetallesVenta detallesVenta);
    DetallesVenta buscarPorId(Integer id);
    void eliminar(Integer id);
}
