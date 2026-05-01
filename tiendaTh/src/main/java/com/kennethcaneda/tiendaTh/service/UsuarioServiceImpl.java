package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Usuarios;
import com.kennethcaneda.tiendaTh.exception.ResourceNotFoundException;
import com.kennethcaneda.tiendaTh.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuarios> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuarios crear(Usuarios usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent() || usuarioRepository.findByUsername(usuario.getUsername()).isPresent()){
            throw  new ResourceNotFoundException("El correo o nombre de usuario ya están en uso");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setCodigoUsuario(null);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuarios actualizar(Integer id, Usuarios usuario) {
        Usuarios usuarioExistente = buscarPorId(id);
        usuarioExistente.setUsername(usuario.getUsername());
        String hash = passwordEncoder.encode(usuario.getPassword());
        usuarioExistente.setPassword(hash);
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setRol(usuario.getRol());
        usuarioExistente.setEstado(usuario.getEstado());
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public Usuarios buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!usuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

}
