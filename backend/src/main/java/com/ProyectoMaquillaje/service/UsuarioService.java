package com.ProyectoMaquillaje.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.model.Respuestas;
import com.ProyectoMaquillaje.model.Usuario;
import com.ProyectoMaquillaje.repository.RepositorioProducto;
import com.ProyectoMaquillaje.repository.RepositorioUsuario;

@Service
public class UsuarioService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;
    @Autowired
    private RepositorioProducto repositorioProducto;

    // Registrar usuario con sus respuestas correctamente
    public Usuario registrarUsuario(Usuario usuario) {
    if (usuario.getRespuestas() != null) {
        // Crear una copia de las respuestas para evitar modificar la lista original durante la iteración
        List<Respuestas> respuestas = new ArrayList<>(usuario.getRespuestas());
        for (Respuestas respuesta : respuestas) {
            usuario.addRespuesta(respuesta); 
            List<Producto> productosRecomendados = obtenerProductosSegunRespuesta(respuesta);
            respuesta.setProductos(productosRecomendados);
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

    // Recomendar productos automáticamente en base a las respuestas del usuario
    public List<Producto> recomendarProductos(String nombreUsuario) {
        // Buscar el usuario por nombre
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar productos que coincidan con las respuestas del usuario
        List<Producto> productosRecomendados = repositorioProducto.findProductosRecomendados(nombreUsuario);

        // Crear explícitamente las relaciones PREFIERE en Neo4j
        for (Producto producto : productosRecomendados) {
            usuario.addProducto(producto); // Agregar producto a la lista
        }

        // Guardar el usuario con las relaciones PREFIERE
        repositorioUsuario.save(usuario);

        return productosRecomendados;
    }

    private List<Producto> obtenerProductosSegunRespuesta(Respuestas respuesta) {
        // Busca productos que coincidan con tonoDePiel, acabado y cobertura
        return repositorioProducto.recomendarPorRespuestas(
            respuesta.getTonoDePiel(),
            respuesta.getAcabado(),
            respuesta.getCobertura()
        );
    }
    
}
