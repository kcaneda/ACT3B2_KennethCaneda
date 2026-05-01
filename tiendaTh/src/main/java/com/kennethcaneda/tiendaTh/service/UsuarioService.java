package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Usuarios;

import java.util.List;

public interface UsuarioService {
    List<Usuarios> listar();
    Usuarios crear(Usuarios usuario);
    Usuarios actualizar(Integer id, Usuarios usuario);
    Usuarios buscarPorId(Integer id);
    void eliminar(Integer id);
}
