package com.ProyectoMaquillaje.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.ProyectoMaquillaje.model.Producto;

// buscar producto por tono de piel
// buscar producto por nombre
@Repository
public interface RepositorioProducto extends Neo4jRepository<Producto, Long> {
    List<Producto> findByTonoDePiel(String tonoDePiel);
}
