package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Clientes;

import java.math.BigInteger;
import java.util.List;

public interface ClienteService {
    List<Clientes> listar();
    Clientes crear(Clientes cliente);
    Clientes actualizar(Long id, Clientes cliente);
    Clientes buscarPorId(Long id);
    void eliminar(Long id);
}
