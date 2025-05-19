package com.ProyectoMaquillaje.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.model.Respuestas;
import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioBlush;
import com.ProyectoMaquillaje.repository.RepositorioConcelear;
import com.ProyectoMaquillaje.repository.RepositorioRimel;
import com.ProyectoMaquillaje.repository.RepositorioUsuario;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioConcelear repositorioConcelear;
    @Autowired
    private RepositorioBlush repositorioBlush;
    @Autowired
    private RepositorioRimel repositorioRimel;

    // registrar usuario con sus respuestas correctamente
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuario.getRespuestas() != null) {
            List<Respuestas> respuestas = new ArrayList<>(usuario.getRespuestas());
            for (Respuestas respuesta : respuestas) {
                usuario.addRespuesta(respuesta); 
                List<Concelear> correctoresRecomendados = obtenerCorrectoresSegunRespuesta(respuesta);
                for (Concelear corrector : correctoresRecomendados) {
                    usuario.addCorrector(corrector); 
                }
            }
        }
        return repositorioUsuario.save(usuario);
    }

    // registrar usuario y productos recomendados
    public Usuario registrarUsuarioConCorrectores(Usuario usuario, List<Concelear> correctores) {
        if (correctores != null) {
            for (Concelear corrector : correctores) {
                usuario.addCorrector(corrector); 
            }
        }
        return repositorioUsuario.save(usuario);
    }

    // obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return repositorioUsuario.findAll();
    }

    // buscar usuario por nombre
    public Optional<Usuario> buscarPorNombre(String nombre) {
        return repositorioUsuario.findByNombre(nombre);
    }

    // recomendar productos automáticamente en base a las respuestas del usuario
    public List<Concelear> recomendarCorrectores(String nombreUsuario) {
        // buscar el usuario por nombre
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // buscar productos que coincidan con las respuestas del usuario
        List<Concelear> correctoresRecomendados = repositorioConcelear.recomendarPorUsuario(nombreUsuario);
        usuario.getCorrector().clear(); // Limpia relaciones viejas
        usuario.getCorrector().addAll(correctoresRecomendados);
        repositorioUsuario.save(usuario);
        return correctoresRecomendados;
    }

    // recomendar blush
    public List<Blush> recomendarBlushes(String nombreUsuario) {
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Blush> blushesRecomendados = repositorioBlush.recomendarPorUsuario(nombreUsuario);
        usuario.getBlushes().clear();
        usuario.getBlushes().addAll(blushesRecomendados);
        repositorioUsuario.save(usuario);
        return blushesRecomendados;
    }

    // recomendar rimel
    public List<Rimel> recomendarRimels(String nombreUsuario) {
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        List<Rimel> rimelsRecomendados = repositorioRimel.recomendarPorUsuario(nombreUsuario);
        usuario.getRimels().clear();
        usuario.getRimels().addAll(rimelsRecomendados);
        repositorioUsuario.save(usuario);
        return rimelsRecomendados;
    }
 
    private List<Concelear> obtenerCorrectoresSegunRespuesta(Respuestas respuesta) {
        // busca productos que coincidan con tonoDePiel, acabado y cobertura
        return repositorioConcelear.recomendarPorRespuestas(
            respuesta.getTonoDePiel(),
            respuesta.getAcabado(),
            respuesta.getCobertura()
        );
    }
    
}
