package com.kennethcaneda.tiendaTh.controller;

import com.kennethcaneda.tiendaTh.entity.Ventas;
import com.kennethcaneda.tiendaTh.service.ClienteService;
import com.kennethcaneda.tiendaTh.service.UsuarioService;
import com.kennethcaneda.tiendaTh.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ventas")
public class VentaController {
    @Autowired
    private final VentaService ventaService;
    private final UsuarioService usuarioService;
    private final ClienteService clienteService;

    public VentaController(VentaService ventaService, UsuarioService usuarioService, ClienteService clienteService) {
        this.ventaService = ventaService;
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("venta", ventaService.listar());
        model.addAttribute("nuevaVenta", new Ventas());
        enviarListas(model);
        return "ventas";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("nuevaVenta") @Valid Ventas venta, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("venta", ventaService.listar());
            model.addAttribute("mostrarModalCrear", true);
            enviarListas(model);
            return "ventas";
        }
        ventaService.crear(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String formularioActualizar(@PathVariable Integer id, Model model){
        model.addAttribute("venta", ventaService.listar());
        model.addAttribute("nuevaVenta", new Ventas());
        model.addAttribute("ventaAEditar", ventaService.buscarPorId(id));
        enviarListas(model);
        return "ventas";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("ventaAEditar") Ventas venta, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("venta", ventaService.listar());
            model.addAttribute("nuevaVenta", new Ventas());
            model.addAttribute("mostrarModalEditar", true);
            enviarListas(model);
            return "ventas";
        }
        ventaService.actualizar(id, venta);
        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String mostrarEliminar(@PathVariable Integer id, Model model){
        model.addAttribute("venta", ventaService.listar());
        model.addAttribute("nuevaVenta", new Ventas());
        model.addAttribute("ventaAEliminar", ventaService.buscarPorId(id));
        return "ventas";
    }

    @PostMapping("/eliminar/{id}")
    private String eliminar(@PathVariable Integer id){
        ventaService.eliminar(id);
        return "redirect:/ventas";
    }

    public void enviarListas(Model model){
        model.addAttribute("listaUsuario", usuarioService.listar());
        model.addAttribute("listaCliente", clienteService.listar());
    }

}
