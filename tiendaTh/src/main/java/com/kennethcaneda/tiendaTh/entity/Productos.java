package com.kennethcaneda.tiendaTh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @Column(name = "nombre_producto")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 a 60 carácteres")
    private String nombreProducto;

    @Column(name = "precio")
    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 1)
    private BigDecimal precio;

    @Column(name = "stock")
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0)
    private Integer stock;

    @Column(name = "estado")
    @NotNull(message = "El estado no puede ir vacío")
    private Boolean estado;

    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(Integer codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
