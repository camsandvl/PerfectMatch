package com.ProyectoMaquillaje.controller;

import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class ControllerUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // Guarda un usuario y sus respuestas
    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    // Devuelve todos los usuarios
    @GetMapping("/todos")
    public List<Usuario> obtenerTodos() {
        return repositorioUsuario.findAll();
    }
}
