package com.ProyectoMaquillaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.repository.RepositorioProducto;

@Service
public class ProductoService {

    @Autowired
    private RepositorioProducto repositorioProducto;

    public Producto guardarProducto(Producto producto) {
        return repositorioProducto.save(producto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return repositorioProducto.findAll();
    }
    public Optional<Producto> buscarPorNombre(String nombre) {
        return repositorioProducto.findByNombre(nombre);
    }
    
}
