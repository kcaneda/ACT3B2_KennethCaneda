package com.kennethcaneda.tiendaTh.controller;

import com.kennethcaneda.tiendaTh.entity.Clientes;
import com.kennethcaneda.tiendaTh.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("cliente", clienteService.listar());
        model.addAttribute("nuevoCliente", new Clientes());
        return "clientes";
    }

    @PostMapping("/crear")
    public String crear(@Valid @ModelAttribute("nuevoCliente") Clientes cliente, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("cliente", clienteService.listar());
            model.addAttribute("mostrarModalCrear", true);
            return "clientes";
        }
        clienteService.crear(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Long id, Model model){
        model.addAttribute("cliente", clienteService.listar());
        model.addAttribute("nuevoCliente", new Clientes());
        model.addAttribute("clienteAEditar", clienteService.buscarPorId(id));
        return "clientes";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Long id, @ModelAttribute("clienteAEditar") Clientes cliente, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("cliente", clienteService.listar());
            model.addAttribute("nuevoCliente", new Clientes());
            model.addAttribute("mostrarModalEditar", true);
            return "clientes";
        }
        clienteService.actualizar(id, cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String mostrarEliminar(@PathVariable Long id, Model model){
        model.addAttribute("cliente", clienteService.listar());
        model.addAttribute("nuevoCliente", new Clientes());
        model.addAttribute("clienteAEliminar", clienteService.buscarPorId(id));
        return "clientes";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id){
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }

}
