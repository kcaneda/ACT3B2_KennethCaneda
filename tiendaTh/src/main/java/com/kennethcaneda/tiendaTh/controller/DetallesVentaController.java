package com.kennethcaneda.tiendaTh.controller;

import com.kennethcaneda.tiendaTh.entity.DetallesVenta;
import com.kennethcaneda.tiendaTh.service.DetallesVentaService;
import com.kennethcaneda.tiendaTh.service.ProductoService;
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
@RequestMapping("/detalles")
public class DetallesVentaController {
    @Autowired
    private final DetallesVentaService detallesVentaService;
    private final ProductoService productoService;
    private final VentaService ventaService;

    public DetallesVentaController(DetallesVentaService detallesVentaService, ProductoService productoService, VentaService ventaService) {
        this.detallesVentaService = detallesVentaService;
        this.productoService = productoService;
        this.ventaService = ventaService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("detalle", detallesVentaService.listar());
        model.addAttribute("nuevoDetalle", new DetallesVenta());
        enviarListas(model);
        return "detalles";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("nuevoDetalle") @Valid DetallesVenta detalle, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("detalle", detallesVentaService.listar());
            model.addAttribute("mostrarModalCrear", true);
            enviarListas(model);
            return "detalles";
        }
        detallesVentaService.crear(detalle);
        return "redirect:/detalles";
    }

    @GetMapping("/editar/{id}")
    public String formularioActualizar(@PathVariable Integer id, Model model){
        model.addAttribute("detalle", detallesVentaService.listar());
        model.addAttribute("nuevoDetalle", new DetallesVenta());
        model.addAttribute("detalleAEditar", detallesVentaService.buscarPorId(id));
        enviarListas(model);
        return "detalles";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("detalleAEditar") @Valid DetallesVenta detalle, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("detalle", detallesVentaService.listar());
            model.addAttribute("nuevoDetalle", new DetallesVenta());
            model.addAttribute("mostrarModalEditar", true);
            enviarListas(model);
            return "detalles";
        }
        detallesVentaService.actualizar(id, detalle);
        return "redirect:/detalles";
    }

    @GetMapping("/eliminar/{id}")
    public String mostrarEliminar(@PathVariable Integer id, Model model){
        model.addAttribute("detalle", detallesVentaService.listar());
        model.addAttribute("nuevoDetalle", new DetallesVenta());
        model.addAttribute("detalleAEliminar", detallesVentaService.buscarPorId(id));
        return "detalles";
    }

    @PostMapping("eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        detallesVentaService.eliminar(id);
        return "redirect:/detalles";
    }

    public void enviarListas(Model model){
        model.addAttribute("listaVenta", ventaService.listar());
        model.addAttribute("listaProducto", productoService.listar());
    }
}
