package com.ProyectoMaquillaje.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.ProyectoMaquillaje.model.Producto;

@Repository
public interface RepositorioProducto extends Neo4jRepository<Producto, Long> {

    // Esta consulta busca productos que coincidan con las respuestas del usuario
    @Query("""
        MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas),
              (p:Producto)
        WHERE 
            p.tonoDePiel = r.tonoDePiel AND
            r.acabado IN p.acabado AND
            p.cobertura = r.cobertura
        RETURN p
        """)
    List<Producto> recomendarPorUsuario(String nombreUsuario);
}
