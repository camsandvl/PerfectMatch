package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.repository.RepositorioRimel;

@RestController
@RequestMapping("/api/rimel")
@CrossOrigin(origins = "*")
public class ControllerRimel {

    @Autowired
    private RepositorioRimel repositorioRimel;

    // endpoint para recomendar rimel por parámetros
    @GetMapping("/recomendar/{color}/{waterproof}/{funcion}")
    public List<Rimel> recomendarRimel(
        @PathVariable String color,
        @PathVariable String waterproof,
        @PathVariable String funcion) {
    boolean isWaterproof = Boolean.parseBoolean(waterproof);
    return repositorioRimel.recomendarPorRespuestas(color, isWaterproof, funcion);
}
}