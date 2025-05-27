package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioBlush;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;
import com.ProyectoMaquillaje.repository.RepositorioRimel;
import com.ProyectoMaquillaje.service.UsuarioService;

@Controller
@RequestMapping("/")
public class ControllerPaginas {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RepositorioBlush repositorioBlush;

    @Autowired
    private RepositorioConcelear repositorioConcelear;

    @Autowired
    private RepositorioRimel repositorioRimel;

    // Páginas HTML
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
        // busca el usuario por nombre y verifica si la contraseña es correcta
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
        redirectAttributes.addFlashAttribute("success", "Usuario registrado correctamente");
        return "redirect:/login";
    }

    // Recomendaciones de correctores
    @GetMapping("/vista/recomendaciones")
    public String mostrarRecomendaciones(
            @RequestParam(required = false) String tonoDePiel,
            @RequestParam(required = false) String acabado,
            @RequestParam(required = false) String cobertura,
            @RequestParam(required = false) String usuario,
            Model model) {
        List<Concelear> correctores;
        if (usuario != null && !usuario.isEmpty()) {
            correctores = usuarioService.recomendarCorrectores(usuario);
        } else {
            correctores = repositorioConcelear.recomendarPorRespuestas(
                    tonoDePiel != null ? tonoDePiel : "",
                    acabado != null ? acabado : "",
                    cobertura != null ? cobertura : "");
        }
        model.addAttribute("productos", correctores);
        return "results";
    }

    // Recomendaciones de blush
    @GetMapping("/vista/recomendaciones-blush")
    public String mostrarRecomendacionesBlush(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String acabado,
            @RequestParam(required = false) String presentacion,
            @RequestParam(required = false) String tonoBlush,
            Model model) {
        List<Blush> blushes;
        if (usuario != null && !usuario.isEmpty()) {
            blushes = usuarioService.recomendarBlushes(usuario);
        } else {
            blushes = repositorioBlush.recomendarPorRespuestas(
                    acabado != null ? acabado : "",
                    presentacion != null ? presentacion : "",
                    tonoBlush != null ? tonoBlush : "");
        }
        model.addAttribute("productos", blushes);
        return "results";
    }

    // Recomendaciones de rimel
    @GetMapping("/vista/recomendaciones-rimel")
    public String mostrarRecomendacionesRimel(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String waterproof,
            @RequestParam(required = false) String funcion,
            Model model) {
        List<Rimel> rimels;
        if (usuario != null && !usuario.isEmpty()) {
            rimels = usuarioService.recomendarRimels(usuario);
        } else {
            boolean isWaterproof = waterproof != null && (waterproof.equalsIgnoreCase("true") || waterproof.equals("1"));
            rimels = repositorioRimel.recomendarPorRespuestas(
                    color != null ? color : "",
                    isWaterproof,
                    funcion != null ? funcion : "");
        }
        model.addAttribute("productos", rimels);
        return "results";
    }
}