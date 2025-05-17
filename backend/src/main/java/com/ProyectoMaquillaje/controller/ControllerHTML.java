package com.ProyectoMaquillaje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ControllerHTML {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/")
    public String mostrarIndex() {
        return "index"; 
    }
    
    @GetMapping("/quiz1")
    public String mostrarQuiz1() {
    return "quiz1";
    }

    @GetMapping("/quiz2")
    public String mostrarQuiz2() {
    return "quiz2";
    }

    @GetMapping("/quiz3")
    public String mostrarQuiz3() {
    return "quiz3";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
    return "login";
    }

    @PostMapping("/login")
public String login(
    @RequestParam("username") String username,
    @RequestParam("password") String password,
    RedirectAttributes redirectAttributes
    ) {
        // Busca el usuario por nombre
        var usuarioOpt = usuarioService.buscarPorNombre(username);
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos");
            return "redirect:/login";
        }
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
    return "dashboard";
    }

    @GetMapping("/register")
    public String mostrarRegistro() {
    return "register";
    }

     @PostMapping("/register")
    public String register(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        RedirectAttributes redirectAttributes
    ) {
        Usuario usuario = new Usuario();
        usuario.setNombre(username);
        usuario.setPassword(password);
        usuarioService.registrarUsuario(usuario);
        redirectAttributes.addFlashAttribute("success", "Usuario registrado correctamente");
        return "redirect:/login";
    }
}
