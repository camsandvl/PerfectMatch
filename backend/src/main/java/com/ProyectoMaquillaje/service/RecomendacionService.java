package com.ProyectoMaquillaje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;

@Service
public class RecomendacionService {

    @Autowired
    private RepositorioConcelear repositorioConcelear;

    // obtiene recomendaciones y crea la relación PREFIERE
    public List<Concelear> recomendarYGuardar(String nombreUsuario) {
        List<Concelear> recomendados = repositorioConcelear.recomendarPorUsuario(nombreUsuario);

        if (!recomendados.isEmpty()) {
            Concelear recomendado = recomendados.get(0); 
            repositorioConcelear.crearRelacionPrefiere(nombreUsuario, recomendado.getNombre());
        }

        return recomendados;
    }
}
