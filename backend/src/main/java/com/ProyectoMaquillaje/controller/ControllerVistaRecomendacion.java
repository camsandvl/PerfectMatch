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

    // Muestra resultados HTML con productos recomendados según las respuestas
    @GetMapping("/recomendaciones")
    public String mostrarRecomendaciones(
            @RequestParam(required = false) String tonoDePiel,
            @RequestParam(required = false) String acabado,
            @RequestParam(required = false) String cobertura,
            Model model) {

        // Log para verificar los parámetros recibidos
        System.out.println("Parámetros recibidos: tonoDePiel=" + tonoDePiel + ", acabado=" + acabado + ", cobertura=" + cobertura);

        List<Concelear> correctores = repositorioConcelear.recomendarPorRespuestas(
                tonoDePiel != null ? tonoDePiel : "",
                acabado != null ? acabado : "",
                cobertura != null ? cobertura : ""
        );

        // Log para verificar los productos obtenidos
        System.out.println("Correctores recomendados: " + correctores);

        model.addAttribute("productos", correctores);
        return "results";  // Renderizar la plantilla results.html
    }
}
