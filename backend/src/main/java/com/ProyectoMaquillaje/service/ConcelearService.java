package com.ProyectoMaquillaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;

@Service
public class ConcelearService {

    @Autowired
    private RepositorioConcelear repositorioConcelear;

    public Concelear guardarCorrector(Concelear corrector) {
        return repositorioConcelear.save(corrector);
    }

    public List<Concelear> obtenerTodosLosCorrectores() {
        return repositorioConcelear.findAll();
    }
    public Optional<Concelear> buscarPorNombre(String nombre) {
        return repositorioConcelear.findByNombre(nombre);
    }

}
