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
        // Guarda el usuario en la base de datos
        usuarioService.registrarUsuario(usuario);
        redirectAttributes.addFlashAttribute("success", "Usuario registrado correctamente");
        return "redirect:/login";
    }

    // Recomendaciones de correctores - obtener producto
    @GetMapping("/vista/recomendaciones-correctores")
    public String mostrarRecomendacionesCorrectores(
        @RequestParam String tonoDePiel,
        @RequestParam String acabado,
        @RequestParam String cobertura,
        @RequestParam(required = false) String usuario,
        Model model
    ) {
        List<Concelear> correctores = repositorioConcelear.recomendarPorRespuestas(tonoDePiel, acabado, cobertura);
        model.addAttribute("productos", correctores);
        if (usuario != null && !usuario.isEmpty()) {
            for (Concelear corrector : correctores) {
                repositorioConcelear.crearRelacionPrefiereConcelear(
                    usuario,
                    corrector.getNombre(),
                    corrector.getTonoDePiel(),
                    corrector.getAcabado(),
                    corrector.getCobertura()
                );
            }
        }
        return "results"; 
    }

    //recomendacion de blush - crear relación con ids 
    @PostMapping("/api/preferencia/concelear")
    public void crearRelacionPrefiereConcelear(
            @RequestParam String usuario,
            @RequestParam String nombre,
            @RequestParam String tonoDePiel,
            @RequestParam String acabado,
            @RequestParam String cobertura
    ) {
        repositorioConcelear.crearRelacionPrefiereConcelear(usuario, nombre, tonoDePiel, acabado, cobertura);
    }

    // Recomendaciones de blush- obtener producto
    @GetMapping("/vista/recomendaciones-blush")
    public String mostrarRecomendacionesBlush(
        @RequestParam String acabado,
        @RequestParam String presentacion,
        @RequestParam String tonoBlush,
        @RequestParam(required = false) String usuario,
        Model model
    ) {
        List<Blush> blushes = repositorioBlush.recomendarPorRespuestas(acabado, presentacion, tonoBlush);
        model.addAttribute("productos", blushes);
        if (usuario != null && !usuario.isEmpty()) {
            for (Blush blush : blushes) {
                repositorioBlush.crearRelacionPrefiereBlush(
                    usuario,
                    blush.getNombre(),
                    blush.getPresentacion(),
                    blush.getAcabado(),
                    blush.getTonoBlush()
                );
            }
        }
        return "resultsBlush"; 
    }

    //recomendacion de blush - crear relación con ids 
    @PostMapping("/api/preferencia/blush")
    public void crearRelacionPrefiereBlush(
            @RequestParam String usuario,
            @RequestParam String nombreBlush,
            @RequestParam String presentacion,
            @RequestParam String acabado,
            @RequestParam String tonoBlush
    ) {
        repositorioBlush.crearRelacionPrefiereBlush(usuario, nombreBlush, presentacion, acabado, tonoBlush);
    }


    
    // Recomendaciones de rimel - obtener producto
    @GetMapping("/vista/recomendaciones-rimel")
    public String mostrarRecomendacionesRimel(
        @RequestParam String color,
        @RequestParam String waterproof,
        @RequestParam String funcion,
        @RequestParam(required = false) String usuario,
        Model model
    ) {
        boolean isWaterproof = waterproof.equalsIgnoreCase("true") || waterproof.equalsIgnoreCase("si");
        List<Rimel> rimels = repositorioRimel.recomendarPorRespuestas(color, isWaterproof, funcion);
        model.addAttribute("productos", rimels);
        if (usuario != null && !usuario.isEmpty()) {
            for (Rimel rimel : rimels) {
                repositorioRimel.crearRelacionPrefiereRimel(
                    usuario,
                    rimel.getNombre(),
                    rimel.getColor(),
                    rimel.isWaterproof(), 
                    rimel.getFuncion()
                );
            }
        }
        return "resultsRimel"; 
    }

    @PostMapping("/api/preferencia/rimel")
    public void crearRelacionPrefiereRimel(
            @RequestParam String usuario,
            @RequestParam String nombreRimel,
            @RequestParam String color,
            @RequestParam String waterproof,
            @RequestParam String funcion
    ) {
        boolean waterproofBool = waterproof.equalsIgnoreCase("true") || waterproof.equalsIgnoreCase("si");
        repositorioRimel.crearRelacionPrefiereRimel(usuario, nombreRimel, color, waterproofBool, funcion);
    }
}