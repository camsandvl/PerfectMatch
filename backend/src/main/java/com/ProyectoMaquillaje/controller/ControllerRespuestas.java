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

    @PostMapping("/guardar")
    public Respuestas guardarRespuestas(@RequestBody Respuestas respuestas) {
        return repositorioRespuestas.save(respuestas);
    }

    @GetMapping("/todas")
    public List<Respuestas> obtenerTodas() {
        return repositorioRespuestas.findAll();
    }
}
