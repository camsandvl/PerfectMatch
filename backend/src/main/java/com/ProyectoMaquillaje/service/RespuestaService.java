package com.ProyectoMaquillaje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Respuestas;
import com.ProyectoMaquillaje.repository.RepositorioRespuestas;

@Service
public class RespuestaService {

    @Autowired
    private RepositorioRespuestas repositorioRespuestas;

    public Respuestas guardarRespuestas(Respuestas respuestas) {
        return repositorioRespuestas.save(respuestas);
    }

    public List<Respuestas> obtenerTodasLasRespuestas() {
        return repositorioRespuestas.findAll();
    }
}
