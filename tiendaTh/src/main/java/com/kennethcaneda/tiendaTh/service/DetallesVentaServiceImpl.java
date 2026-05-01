package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.DetallesVenta;
import com.kennethcaneda.tiendaTh.exception.ResourceNotFoundException;
import com.kennethcaneda.tiendaTh.repository.DetallesVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallesVentaServiceImpl implements DetallesVentaService {
    private final DetallesVentaRepository detallesVentaRepository;

    public DetallesVentaServiceImpl(DetallesVentaRepository detallesVentaRepository) {
        this.detallesVentaRepository = detallesVentaRepository;
    }

    @Override
    public List<DetallesVenta> listar() {
        return detallesVentaRepository.findAll();
    }

    @Override
    public DetallesVenta crear(DetallesVenta detallesVenta) {
        detallesVenta.setCodigoDetalleVenta(null);
        return detallesVentaRepository.save(detallesVenta);
    }

    @Override
    public DetallesVenta actualizar(Integer id, DetallesVenta detallesVenta) {
        DetallesVenta detallesExistentes = buscarPorId(id);
        detallesExistentes.setCantidad(detallesVenta.getCantidad());
        detallesExistentes.setPrecioUnitario(detallesVenta.getPrecioUnitario());
        detallesExistentes.setSubtotal(detallesVenta.getSubtotal());
        detallesExistentes.setVentasCodigoVenta(detallesVenta.getVentasCodigoVenta());
        detallesExistentes.setProductosCodigoProducto(detallesVenta.getProductosCodigoProducto());
        return detallesVentaRepository.save(detallesExistentes);
    }

    @Override
    public DetallesVenta buscarPorId(Integer id) {
        return detallesVentaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Detalle no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!detallesVentaRepository.existsById(id)){
            throw new ResourceNotFoundException("Detalle no encontrado con id: " + id);
        }
        detallesVentaRepository.deleteById(id);
    }
}
