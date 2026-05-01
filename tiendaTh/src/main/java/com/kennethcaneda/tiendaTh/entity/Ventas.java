package com.kennethcaneda.tiendaTh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Integer codigoVenta;

    @Column(name = "fecha_venta")
    @PastOrPresent(message = "Ingrese una fecha válida")
    private LocalDate fechaVenta;

    @Column(name = "total")
    @NotNull(message = "El total no puede ser nulo")
    @Min(value = 1)
    private BigDecimal total;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "clientes_dpi_cliente")
    @NotNull(message = "El dpi del cliente no puede ser nulo")
    private Long clientesDpiCliente;

    @Column(name = "usuarios_codigo_usuario")
    @NotNull(message = "El código del usuario no puede ser nulo")
    private Integer usuariosCodigoUsuario;

    public Integer getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Integer codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getClientesDpiCliente() {
        return clientesDpiCliente;
    }

    public void setClientesDpiCliente(Long clientesDpiCliente) {
        this.clientesDpiCliente = clientesDpiCliente;
    }

    public Integer getUsuariosCodigoUsuario() {
        return usuariosCodigoUsuario;
    }

    public void setUsuariosCodigoUsuario(Integer usuariosCodigoUsuario) {
        this.usuariosCodigoUsuario = usuariosCodigoUsuario;
    }
}
