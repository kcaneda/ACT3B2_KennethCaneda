package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.enumtypes.UserRoles;
import com.kennethcaneda.tiendaTh.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.kennethcaneda.tiendaTh.entity.Usuarios;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public AuthService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuarioRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con correo: " + username));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }

    public boolean register(String username, String email, String password){
        if (usuarioRepository.findByEmail(email).isPresent() || usuarioRepository.findByUsername(username).isPresent()){
            return false;
        }
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setEstado(true);
        nuevoUsuario.setRol(UserRoles.USUARIO);
        usuarioRepository.save(nuevoUsuario);
        return true;
    }
}