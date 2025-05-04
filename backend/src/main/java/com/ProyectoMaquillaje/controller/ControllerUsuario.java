package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioUsuario;

@RestController
@RequestMapping("/api/usuarios") 
@CrossOrigin(origins = "*")
public class ControllerUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // guarda un usuario y sus respuestas
    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    // muestra todos los usuarios con getmapping
    @GetMapping("/todos")
    public List<Usuario> obtenerTodos() {
        return repositorioUsuario.findAll();
    }
}
