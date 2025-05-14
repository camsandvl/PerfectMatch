package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Respuestas;
import com.ProyectoMaquillaje.repository.RepositorioRespuestas;

@RestController
@RequestMapping("/api/respuestas")
@CrossOrigin(origins = "*") 
public class ControllerRespuestas {

    @Autowired
    private RepositorioRespuestas repositorioRespuestas;
    //concelear
    @PostMapping("/guardar-concelear")
    public Respuestas guardarRespuestas(@RequestBody Respuestas respuestas) {
        respuestas.setTipo("concelear");
        return repositorioRespuestas.save(respuestas);
    }

    //blush
    @PostMapping("/guardar-blush")
    public Respuestas guardarRespuestasBlush(@RequestBody Respuestas respuestas) {
        respuestas.setTipo("blush");
        return repositorioRespuestas.save(respuestas);
    }

    // rimel
    @PostMapping("/guardar-rimel")
    public Respuestas guardarRespuestasRimel(@RequestBody Respuestas respuestas) {
        respuestas.setTipo("rimel");
        return repositorioRespuestas.save(respuestas);
    }

    @GetMapping("/todas")
    public List<Respuestas> obtenerTodas() {
        return repositorioRespuestas.findAll();
    }
}
