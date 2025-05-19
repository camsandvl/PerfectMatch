package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;


@Controller
@RequestMapping("/vista")
public class ControllerVistaRecomendacion {

    @Autowired
    private RepositorioConcelear repositorioConcelear;

    @Autowired
    private com.ProyectoMaquillaje.service.UsuarioService usuarioService;

    @GetMapping("/recomendaciones")
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
            cobertura != null ? cobertura : ""
        );
    }

    model.addAttribute("productos", correctores);
    return "results";
}
    @GetMapping("/recomendaciones-blush")
    public String mostrarRecomendacionesBlush(@RequestParam String usuario, Model model) {
        List<Blush> blushes = usuarioService.recomendarBlushes(usuario);
        model.addAttribute("productos", blushes);
        return "results";
    }
 
    @GetMapping("/recomendaciones-rimel")
    public String mostrarRecomendacionesRimel(@RequestParam String usuario, Model model) {
        List<Rimel> rimels = usuarioService.recomendarRimels(usuario);
        model.addAttribute("productos", rimels);
        return "results";
    } 
}
