package com.kennethcaneda.tiendaTh.entity;

import com.kennethcaneda.tiendaTh.enumtypes.UserRoles;
import com.kennethcaneda.tiendaTh.enumtypes.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigoUsuario;

    @Column(name = "username")
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 45, message = "El nombre debe tener entre 3 a 45 carácteres")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 60, message = "La constraseña debe tener entre 8 a 60 carácteres")
    private String password;

    @Email
    @Column(name = "email")
    @NotBlank(message = "El email no puede estar vacío")
    @Size(min = 6, max = 60, message = "El email debe tener entre 6 a 60 carácteres")
    private String email;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private UserRoles rol;

    @Column(name = "estado")
    private Boolean estado;

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public UserRoles getRol() {
        return rol;
    }

    public void setRol(UserRoles rol) {
        this.rol = rol;
    }
}
