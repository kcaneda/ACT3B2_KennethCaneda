package com.kennethcaneda.tiendaTh.controller;

import com.kennethcaneda.tiendaTh.entity.Usuarios;
import com.kennethcaneda.tiendaTh.repository.UsuarioRepository;
import com.kennethcaneda.tiendaTh.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private final AuthService authService;
    private final UsuarioRepository usuarioRepository;

    public HomeController(AuthService authService, UsuarioRepository usuarioRepository) {
        this.authService = authService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuarios usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("No se encontró el usuario con correo: " + email));
        model.addAttribute("nombreUsuario", usuario.getUsername());
        return "home";
    }

    @GetMapping("/")
    public String redirectHome(){
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/acceso-denegado")
    public String errorAcceso(){
        return "acceso-denegado";
    }

    @PostMapping("/register")
    public String registerForm(@ModelAttribute("usuario") @Valid Usuarios usuario, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errores", bindingResult.getAllErrors());
            return "register";
        }

        boolean registroExitoso = authService.register(usuario.getUsername(), usuario.getEmail(), usuario.getPassword());
        if (!registroExitoso) {
            model.addAttribute("errorRegistro", "El email o nombre de usuario ya está en uso");
            return "register";
        }
        return "redirect:/login?registered";
    }

}


