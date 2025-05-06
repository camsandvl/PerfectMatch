package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.repository.RepositorioProducto;

@RestController
@RequestMapping("/api/recomendaciones")
@CrossOrigin(origins = "*")
public class ControllerRecomendacion {

    @Autowired
    private RepositorioProducto repositorioProducto;

    // Este endpoint devuelve productos recomendados según las respuestas del usuario
    @GetMapping("/{tonoDePiel}/{acabado}/{cobertura}")
    public List<Producto> recomendarProductos(
            @PathVariable String tonoDePiel,
            @PathVariable String acabado,
            @PathVariable String cobertura) {
        return repositorioProducto.recomendarPorRespuestas(tonoDePiel, acabado, cobertura);
    }

    @GetMapping("/usuario/{nombreUsuario}")
    public List<Producto> recomendarProductosPorUsuario(@PathVariable String nombreUsuario) {
    return repositorioProducto.recomendarPorUsuario(nombreUsuario);
    }
}
