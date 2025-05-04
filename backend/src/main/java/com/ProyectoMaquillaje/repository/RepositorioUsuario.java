package com.ProyectoMaquillaje.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoMaquillaje.model.Usuario;

//buscar usuario por nombre
@Repository
public interface RepositorioUsuario extends Neo4jRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);
}

