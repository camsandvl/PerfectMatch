package com.ProyectoMaquillaje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioUsuario;
import com.ProyectoMaquillaje.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class ControllerUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @PostMapping("/registrar")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    @GetMapping("/todos")
    public List<Usuario> obtenerTodos() {
        return repositorioUsuario.findAll();
    }

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{nombreUsuario}/recomendaciones")
    public List<Concelear> obtenerRecomendaciones(@PathVariable String nombreUsuario) {
        return usuarioService.recomendarCorrectores(nombreUsuario);
    }
}
