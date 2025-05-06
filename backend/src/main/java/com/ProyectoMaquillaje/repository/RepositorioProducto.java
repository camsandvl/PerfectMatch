package com.ProyectoMaquillaje.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.model.Respuestas;

@Repository
public interface RepositorioProducto extends Neo4jRepository<Producto, Long> {

    @Query("""
        MATCH (p:Producto), (r:Respuestas)
        WHERE p.tonoDePiel = r.tonoDePiel AND
              p.acabado = r.acabado AND
              p.cobertura = r.cobertura
        RETURN p
    """)
    List<Producto> findProductosRecomendados(List<Respuestas> respuestas);

    // Esta consulta busca productos que coincidan con las respuestas del usuario
    @Query("""
    MATCH (p:Producto)
    WHERE 
        p.tonoDePiel = $tonoDePiel AND
        $acabado IN p.acabado AND
        p.cobertura = $cobertura
    RETURN p
    """)
    List<Producto> recomendarPorRespuestas(String tonoDePiel, String acabado, String cobertura);

    @Query("""
        MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas),
            (p:Producto)
        WHERE 
            p.tonoDePiel = r.tonoDePiel AND
            p.acabado = r.acabado AND
            p.cobertura = r.cobertura
        RETURN p
    """)
    List<Producto> recomendarPorUsuario(String nombreUsuario);
}
