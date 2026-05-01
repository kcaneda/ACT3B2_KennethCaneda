package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Ventas;
import com.kennethcaneda.tiendaTh.exception.ResourceNotFoundException;
import com.kennethcaneda.tiendaTh.repository.VentasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService{
    private final VentasRepository ventasRepository;

    public VentaServiceImpl(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> listar() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas crear(Ventas venta) {
        venta.setCodigoVenta(null);
        return ventasRepository.save(venta);
    }

    @Override
    public Ventas actualizar(Integer id, Ventas venta) {
        Ventas ventaExistente = buscarPorId(id);
        ventaExistente.setFechaVenta(venta.getFechaVenta());
        ventaExistente.setTotal(venta.getTotal());
        ventaExistente.setEstado(venta.getEstado());
        ventaExistente.setClientesDpiCliente(venta.getClientesDpiCliente());
        ventaExistente.setUsuariosCodigoUsuario(venta.getUsuariosCodigoUsuario());
        return ventasRepository.save(ventaExistente);
    }

    @Override
    public Ventas buscarPorId(Integer id) {
        return ventasRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Venta no encontrada con id: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!ventasRepository.existsById(id)){
            throw new ResourceNotFoundException("Venta no encontrada con id: " + id);
        }
        ventasRepository.deleteById(id);
    }
}
