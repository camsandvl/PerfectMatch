package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.repository.RepositorioBlush;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;
import com.ProyectoMaquillaje.repository.RepositorioRimel;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ControllerProductos {

    @Autowired
    private RepositorioBlush repositorioBlush;

    @Autowired
    private RepositorioConcelear repositorioConcelear;

    @Autowired
    private RepositorioRimel repositorioRimel;

    // endpoint para recomendar blush
    @GetMapping("/blush/recomendar/{acabado}/{presentacion}/{tonoBlush}")
    public List<Blush> recomendarBlush(
            @PathVariable String acabado,
            @PathVariable String presentacion,
            @PathVariable String tonoBlush) {
        return repositorioBlush.recomendarPorRespuestas(acabado, presentacion, tonoBlush);
    }

    // endpoint para recomendar correctores
    @GetMapping("/concelear/recomendar/{tonoDePiel}/{acabado}/{cobertura}")
    public List<Concelear> recomendarCorrectores(
            @PathVariable String tonoDePiel,
            @PathVariable String acabado,
            @PathVariable String cobertura) {
        return repositorioConcelear.recomendarPorRespuestas(tonoDePiel, acabado, cobertura);
    }

    // endpoint para recomendar rimel
    @GetMapping("/rimel/recomendar/{color}/{waterproof}/{funcion}")
    public List<Rimel> recomendarRimel(
            @PathVariable String color,
            @PathVariable String waterproof,
            @PathVariable String funcion) {
        boolean isWaterproof = Boolean.parseBoolean(waterproof);
        return repositorioRimel.recomendarPorRespuestas(color, isWaterproof, funcion);
    }
}