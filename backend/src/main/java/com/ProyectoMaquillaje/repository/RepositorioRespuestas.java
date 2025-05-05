package com.ProyectoMaquillaje.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoMaquillaje.model.Respuestas;

// buscar respuestas por id de usuario, de esta manera tmb se puede buscar por id de tono de piel, id de producto, etc

@Repository
public interface RepositorioRespuestas extends Neo4jRepository<Respuestas, Long> {
    List<Respuestas> findByUsuarioId(Long usuarioId);
}