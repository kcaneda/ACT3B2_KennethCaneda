package com.kennethcaneda.tiendaTh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "detalles_venta")
public class DetallesVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private Integer codigoDetalleVenta;

    @Column(name = "cantidad")
    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1)
    private Integer cantidad;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @Min(value = 1)
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "subtotal")
    @NotNull(message = "El subtotal no puede ser nulo")
    @Min(value = 1)
    private Double subtotal;

    @Column(name = "productos_codigo_producto")
    @NotNull(message = "El código del producto no puede ser nulo")
    @Min(value = 1)
    private Integer productosCodigoProducto;

    @Column(name = "ventas_codigo_venta")
    @NotNull(message = "El código de la venta no puede ser nulo")
    @Min(value = 1)
    private Integer ventasCodigoVenta;

    public Integer getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(Integer codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getProductosCodigoProducto() {
        return productosCodigoProducto;
    }

    public void setProductosCodigoProducto(Integer productosCodigoProducto) {
        this.productosCodigoProducto = productosCodigoProducto;
    }

    public Integer getVentasCodigoVenta() {
        return ventasCodigoVenta;
    }

    public void setVentasCodigoVenta(Integer ventasCodigoVenta) {
        this.ventasCodigoVenta = ventasCodigoVenta;
    }
}
