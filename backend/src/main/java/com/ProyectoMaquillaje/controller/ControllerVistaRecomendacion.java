package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.repository.RepositorioProducto;

@Controller
@RequestMapping("/vista")
public class ControllerVistaRecomendacion {

    @Autowired
    private RepositorioProducto repositorioProducto;

    // Muestra resultados HTML con productos recomendados según las respuestas
    @GetMapping("/recomendaciones")
    public String mostrarRecomendaciones(
            @RequestParam(required = false) String tonoDePiel,
            @RequestParam(required = false) String acabado,
            @RequestParam(required = false) String cobertura,
            Model model) {

        // Log para verificar los parámetros recibidos
        System.out.println("Parámetros recibidos: tonoDePiel=" + tonoDePiel + ", acabado=" + acabado + ", cobertura=" + cobertura);

        List<Producto> productos = repositorioProducto.recomendarPorRespuestas(
                tonoDePiel != null ? tonoDePiel : "",
                acabado != null ? acabado : "",
                cobertura != null ? cobertura : ""
        );

        // Log para verificar los productos obtenidos
        System.out.println("Productos recomendados: " + productos);

        model.addAttribute("productos", productos);
        return "results";  // Renderizar la plantilla results.html
    }
}
