package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;

@RestController
@RequestMapping("/api/recomendaciones")
@CrossOrigin(origins = "*")
public class ControllerRecomendacion {

    @Autowired
    private RepositorioConcelear repositorioConcelear;

    // Este endpoint devuelve productos recomendados según las respuestas del usuario
    @GetMapping("/{tonoDePiel}/{acabado}/{cobertura}")
    public List<Concelear> recomendarCorrectores(
            @PathVariable String tonoDePiel,
            @PathVariable String acabado,
            @PathVariable String cobertura) {
        return repositorioConcelear.recomendarPorRespuestas(tonoDePiel, acabado, cobertura);
    }

    @GetMapping("/usuario/{nombreUsuario}")
    public List<Concelear> recomendarCorrectoresPorUsuario(@PathVariable String nombreUsuario) {
        return repositorioConcelear.recomendarPorUsuario(nombreUsuario);
    }
}
