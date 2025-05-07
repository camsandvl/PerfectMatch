package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            @RequestParam String tonoDePiel,
            @RequestParam String acabado,
            @RequestParam String cobertura,
            Model model) {

        List<Producto> productos = repositorioProducto.recomendarPorRespuestas(tonoDePiel, acabado, cobertura);
        model.addAttribute("productos", productos);
        return "results";  // esto cargará results.html en /templates 
    }


    // Muestra resultados HTML con productos recomendados según el nombre de usuario
    @GetMapping("/usuario/{nombreUsuario}")
    public String mostrarRecomendacionesPorUsuario(@PathVariable String nombreUsuario, Model model) {
        List<Producto> productos = repositorioProducto.recomendarPorUsuario(nombreUsuario);
        model.addAttribute("productos", productos);
        return "results";  
    }
}
