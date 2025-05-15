package com.ProyectoMaquillaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.repository.RepositorioBlush;

@Service
public class BlushService {

    @Autowired
    private RepositorioBlush blushRepository;

    public void guardarBlush(Blush blush) {
        blushRepository.save(blush);
    }

    public List<Blush> obtenerTodosLosBlushes() {
        return blushRepository.findAll();
    }

    public Optional<Blush> buscarPorNombre(String nombre) {
        return blushRepository.findByNombre(nombre);
    }
}
