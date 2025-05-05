package com.ProyectoMaquillaje.controller;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.repository.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recomendaciones")
@CrossOrigin(origins = "*")
public class ControllerRecomendacion {

    @Autowired
    private RepositorioProducto repositorioProducto;

    // Este endpoint devuelve productos recomendados según el usuario
    @GetMapping("/{nombreUsuario}")
    public List<Producto> recomendarProductos(@PathVariable String nombreUsuario) {
        return repositorioProducto.recomendarPorUsuario(nombreUsuario);
    }
}
