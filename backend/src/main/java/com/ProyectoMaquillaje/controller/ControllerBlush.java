package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.repository.RepositorioBlush;

@RestController
@RequestMapping("/api/blush")
@CrossOrigin(origins = "*")
public class ControllerBlush {

    @Autowired
    private RepositorioBlush repositorioBlush;

    // endpoint para recomendar blush por parámetros
    @GetMapping("/recomendar/{acabado}/{presentacion}/{tonoBlush}")
    public List<Blush> recomendarBlush(
            @PathVariable String acabado,
            @PathVariable String presentacion,
            @PathVariable String tonoBlush) {
        return repositorioBlush.recomendarPorRespuestas(acabado, presentacion, tonoBlush);
    }
}