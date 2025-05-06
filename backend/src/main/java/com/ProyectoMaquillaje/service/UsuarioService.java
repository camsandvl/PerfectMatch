package com.ProyectoMaquillaje.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.model.Respuestas;
import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioUsuario;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // Registrar usuario con sus respuestas correctamente
    public Usuario registrarUsuario(Usuario usuario) {
        // Asegurarse que la relación está bien establecida
        if (usuario.getRespuestas() != null) {
            for (Respuestas respuesta : usuario.getRespuestas()) {
                usuario.addRespuesta(respuesta); // Esto agrega la respuesta y crea la relación
            }
        }
        return repositorioUsuario.save(usuario);
    }

    // Registrar usuario y productos recomendados
    public Usuario registrarUsuarioConProductos(Usuario usuario, List<Producto> productos) {
        if (productos != null) {
            for (Producto producto : productos) {
                usuario.addProducto(producto); // Relaciona productos con usuario
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
}
