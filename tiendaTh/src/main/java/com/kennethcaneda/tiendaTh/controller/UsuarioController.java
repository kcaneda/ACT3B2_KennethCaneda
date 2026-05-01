package com.kennethcaneda.tiendaTh.controller;

import com.kennethcaneda.tiendaTh.entity.Usuarios;
import com.kennethcaneda.tiendaTh.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("usuario", usuarioService.listar());
        model.addAttribute("nuevoUsuario", new Usuarios());
        return "usuarios";
    }

    @PostMapping("/crear")
    public String crear(Model model, @Valid @ModelAttribute("nuevoUsuario") Usuarios usuario, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addAttribute("usuario", usuarioService.listar());
            model.addAttribute("mostrarModalCrear", true);
            return "usuarios";
        }
        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Integer id, Model model){
        model.addAttribute("usuario", usuarioService.listar());
        model.addAttribute("nuevoUsuario", new Usuarios());
        model.addAttribute("usuarioAEditar", usuarioService.buscarPorId(id));
        return "usuarios";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("usuarioAEditar") Usuarios usuario, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("usuario", usuarioService.listar());
            model.addAttribute("nuevoUsuario", new Usuarios());
            model.addAttribute("mostrarModalEditar", true);
            return "usuarios";
        }
        usuarioService.actualizar(id, usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String mostrarEliminar(@PathVariable Integer id, Model model){
        model.addAttribute("usuario", usuarioService.listar());
        model.addAttribute("nuevoUsuario", new Usuarios());
        model.addAttribute("usuarioAEliminar", usuarioService.buscarPorId(id));
        return "usuarios";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

    //Métodos Multipágina
//    @PostMapping("/actualizar/{id}")
//    public String editar(@PathVariable @Min(value = 1, message = "El id debe ser mayor o igual a 0") Integer id, @ModelAttribute("usuarios") @Valid Usuarios usuarios, BindingResult bindingResult, Model model){
//        if (bindingResult.hasErrors()){
//            model.addAttribute("modoEdicion", true);
//            return "usuario-form";
//        }
//        usuarioService.actualizar(id, usuarios);
//        return "redirect:/usuarios";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String eliminar2(@PathVariable Integer id){
//        usuarioService.eliminar(id);
//        return "redirect:/usuarios";
//    }
}
