package com.ProyectoMaquillaje.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.model.Respuestas;
import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;
import com.ProyectoMaquillaje.repository.RepositorioUsuario;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioConcelear repositorioConcelear;

    // Registrar usuario con sus respuestas correctamente
    public Usuario registrarUsuario(Usuario usuario) {
    if (usuario.getRespuestas() != null) {
        // Crear una copia de las respuestas para evitar modificar la lista original durante la iteración
        List<Respuestas> respuestas = new ArrayList<>(usuario.getRespuestas());
        for (Respuestas respuesta : respuestas) {
            usuario.addRespuesta(respuesta); 
            List<Concelear> correctoresRecomendados = obtenerCorrectoresSegunRespuesta(respuesta);
            respuesta.setCorrector(correctoresRecomendados);
        }
    }
    return repositorioUsuario.save(usuario);
}

    // Registrar usuario y productos recomendados
    public Usuario registrarUsuarioConCorrectores(Usuario usuario, List<Concelear> correctores) {
        if (correctores != null) {
            for (Concelear corrector : correctores) {
                usuario.addCorrector(corrector); // Relaciona correctores con usuario
            }
        }
        return repositorioUsuario.save(usuario);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return repositorioUsuario.findAll();
    }

    // Buscar usuario por nombre
    public Optional<Usuario> buscarPorNombre(String nombre) {
        return repositorioUsuario.findByNombre(nombre);
    }

    // Recomendar productos automáticamente en base a las respuestas del usuario
    public List<Concelear> recomendarCorrectores(String nombreUsuario) {
        // Buscar el usuario por nombre
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar productos que coincidan con las respuestas del usuario
        List<Concelear> correctoresRecomendados = repositorioConcelear.findCorrectoresRecomendados(nombreUsuario);

        // Crear explícitamente las relaciones PREFIERE en Neo4j
        for (Concelear corrector : correctoresRecomendados) {
            usuario.addCorrector(corrector); // Agregar corrector a la lista
        }

        // Guardar el usuario con las relaciones PREFIERE
        repositorioUsuario.save(usuario);

        return correctoresRecomendados;
    }

    private List<Concelear> obtenerCorrectoresSegunRespuesta(Respuestas respuesta) {
        // Busca productos que coincidan con tonoDePiel, acabado y cobertura
        return repositorioConcelear.recomendarPorRespuestas(
            respuesta.getTonoDePiel(),
            respuesta.getAcabado(),
            respuesta.getCobertura()
        );
    }
    
}
