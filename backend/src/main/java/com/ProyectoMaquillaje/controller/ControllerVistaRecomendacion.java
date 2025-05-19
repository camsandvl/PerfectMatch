package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;


@Controller
@RequestMapping("/vista")
public class ControllerVistaRecomendacion {

    @Autowired
    private RepositorioConcelear repositorioConcelear;

    @Autowired
    private com.ProyectoMaquillaje.service.UsuarioService usuarioService;

    @Autowired
    private com.ProyectoMaquillaje.service.RecomendacionService recomendacionService;

    @GetMapping("/recomendaciones")
    public String mostrarRecomendaciones(
        @RequestParam(required = false) String tonoDePiel,
        @RequestParam(required = false) String acabado,
        @RequestParam(required = false) String cobertura,
        @RequestParam(required = false) String usuario,
        Model model) {

    List<Concelear> correctores;
    if (usuario != null && !usuario.isEmpty()) {
        correctores = usuarioService.recomendarCorrectores(usuario); // ¡ESTO CREA LA RELACIÓN!
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


/*
    @Autowired
    private RepositorioBlush repositorioBlush;
    @GetMapping("/recomendaciones-blush")
    public String mostrarRecomendacionesBlush(
            @RequestParam(required = false) String acabado,
            @RequestParam(required = false) String presentacion,
            @RequestParam(required = false) String tonoBlush,
            @RequestParam(required = false) String usuario,
            Model model) {

        List<Blush> blushes;
        if (usuario != null && !usuario.isEmpty()) {
            blushes = usuarioService.recomendarBlushes(usuario);
        } else {
            blushes = repositorioBlush.recomendarPorRespuestas(
                    acabado != null ? acabado : "",
                    presentacion != null ? presentacion : "",
                    tonoBlush != null ? tonoBlush : ""
            );
        }

        model.addAttribute("productos", blushes);
        return "resultsBlush"; // Crea results-blush.html en templates
    }
 
    @Autowired
    private com.ProyectoMaquillaje.repository.RepositorioRimel repositorioRimel;
    @GetMapping("/recomendaciones-rimel")
    public String mostrarRecomendacionesRimel(
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String waterproof,
            @RequestParam(required = false) String funcion,
            @RequestParam(required = false) String usuario,
            Model model) {
        boolean isWaterproof = Boolean.parseBoolean(waterproof);
        List<Rimel> rimels;
        if (usuario != null && !usuario.isEmpty()) {
            rimels = usuarioService.recomendarRimels(usuario);
        } else {
            rimels = repositorioRimel.recomendarPorRespuestas(
                color != null ? color : "",
                isWaterproof,
                funcion != null ? funcion : ""
            );
        }

        model.addAttribute("productos", rimels);
        return "resultsRimel";
    } */
}
