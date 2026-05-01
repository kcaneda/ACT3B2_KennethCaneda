package com.kennethcaneda.tiendaTh.service;

import com.kennethcaneda.tiendaTh.entity.Clientes;
import com.kennethcaneda.tiendaTh.exception.ResourceNotFoundException;
import com.kennethcaneda.tiendaTh.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Clientes> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public Clientes crear(Clientes cliente) {
        if (clienteRepository.existsByDpiCliente(cliente.getDpiCliente())){
            throw new ResourceNotFoundException("El dpi ya está registrado con otro usuario");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Clientes actualizar(Long id, Clientes cliente) {
        Clientes clienteExistente = buscarPorId(id);
        clienteExistente.setDpiCliente(cliente.getDpiCliente());
        clienteExistente.setNombreCliente(cliente.getNombreCliente());
        clienteExistente.setApellidoCliente(cliente.getApellidoCliente());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setEstado(cliente.getEstado());
        return clienteRepository.save(clienteExistente);
    }

    @Override
    public Clientes buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No se encontró cliente con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)){
            throw new ResourceNotFoundException("No se encontró cliente con id: " + id);
        }
        clienteRepository.deleteById(id);

    }
}
