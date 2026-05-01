package com.kennethcaneda.tiendaTh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigInteger;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Clientes {
    @Id
    @Column(name = "dpi_cliente")
    @Min(value = 1000000000000L, message = "El DPI debe tener 13 dígitos")
    @Max(value = 9999999999999L, message = "El DPI debe tener 13 dígitos")
    @NotNull(message = "El DPI no puede estar vacío")
    private Long dpiCliente;

    @Column(name = "nombre_cliente")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 a 60 carácteres")
    private String nombreCliente;

    @Column(name = "apellido_cliente")
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 3, max = 60, message = "El apellido debe tener entre 3 a 60 carácteres")
    private String apellidoCliente;

    @Column(name = "direccion")
    @NotBlank(message = "La direccion no puede estar vacío")
    @Size(min = 5, max = 100, message = "La direccion debe tener entre 5 a 100 carácteres")
    private String direccion;

    @Column(name = "estado")
    private Boolean estado;

    public Long getDpiCliente() {
        return dpiCliente;
    }

    public void setDpiCliente(Long dpiCliente) {
        this.dpiCliente = dpiCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
