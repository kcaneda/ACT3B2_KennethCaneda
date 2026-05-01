package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Productos;
import com.kennethcaneda.tiendaTh.exception.ResourceNotFoundException;
import com.kennethcaneda.tiendaTh.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Productos> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Productos crear(Productos producto) {
        producto.setCodigoProducto(null);
        return productoRepository.save(producto);
    }

    @Override
    public Productos actualizar(Integer id, Productos producto) {
        Productos productoExistente = buscarPorId(id);
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setStock(producto.getStock());
        productoExistente.setEstado(producto.getEstado());
        return productoRepository.save(productoExistente);
    }

    @Override
    public Productos buscarPorId(Integer id) {
        return productoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)){
            throw new ResourceNotFoundException("No se encontró producto con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
