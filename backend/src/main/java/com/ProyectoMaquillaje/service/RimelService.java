package com.ProyectoMaquillaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.repository.RepositorioRimel;

@Service
public class RimelService {

    @Autowired
    private RepositorioRimel rimelRepository;

    public void guardarRimel(Rimel rimel) {
        rimelRepository.save(rimel);
    }

    public List<Rimel> obtenerTodosLosRimels() {
        return rimelRepository.findAll();
    }

    public Optional<Rimel> buscarPorNombre(String nombre) {
        return rimelRepository.findByNombre(nombre);
    }
}
