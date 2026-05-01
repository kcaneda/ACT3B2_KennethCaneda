package com.kennethcaneda.tiendaTh.controller;

import com.kennethcaneda.tiendaTh.entity.Productos;
import com.kennethcaneda.tiendaTh.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("nuevoProducto", new Productos());
        return "productos";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("nuevoProducto") @Valid Productos producto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("producto", productoService);
            model.addAttribute("mostrarModalCrear", true);
            return "productos";
        }
        productoService.crear(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model){
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("nuevoProducto", new Productos());
        model.addAttribute("productoAEditar", productoService.buscarPorId(id));
        return "productos";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id,  @ModelAttribute("productoAEditar") @Valid Productos producto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("producto", productoService.listar());
            model.addAttribute("nuevoProducto", new Productos());
            model.addAttribute("mostrarModalEditar", true);
            return "productos";
        }
        productoService.actualizar(id, producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String formularioEliminar(@PathVariable Integer id, Model model){
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("nuevoProducto", new Productos());
        model.addAttribute("productoAEliminar", productoService.buscarPorId(id));
        return "productos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
