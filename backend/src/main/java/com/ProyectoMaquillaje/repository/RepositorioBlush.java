package com.ProyectoMaquillaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.model.Respuestas;

public interface RepositorioBlush extends Neo4jRepository<Blush, Long> {

    @Query("""
        MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas),
              (b:Blush)
        WHERE
            b.tonoDePiel = r.tonoDePiel AND
            b.acabado = r.acabado
        RETURN b
    """)
    List<Blush> findBlushRecomendados(@Param("nombreUsuario") String nombreUsuario);

    //solo en lo que no está la implementación de usuario
    @Query("""
        MATCH (r:Respuestas), (b:Blush)
        WHERE
            b.tonoDePiel = r.tonoDePiel AND
            b.acabado = r.acabado AND
            b.presentacion = r.presentacion AND
            b.tonoBlush = r.tonoBlush AND
            b.presentacion = r.presentacion AND
            r IN $respuestas
        RETURN b
    """)
    List<Blush> findBlushesRecomendados(@Param("respuestas") List<Respuestas> respuestas);

    @Query("""
        MATCH (b:Blush)
        WHERE
            b.acabado = $acabado AND
            b.presentacion = $presentacion AND
            b.tonoBlush = $tonoBlush
        RETURN b
    """)
    List<Blush> recomendarPorRespuestas(String acabado, String presentacion, String tonoBlush);

    @Query("""
    MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas)
    MATCH (b:Blush)
    WHERE
        b.tonoDePiel = r.tonoDePiel AND
        b.acabado = r.acabado AND
        b.presentacion = r.presentacion AND
        b.tonoBlush = r.tonoBlush
    RETURN b
    """)
    List<Blush> recomendarPorUsuario(String nombreUsuario);

    @Query("""
        MATCH (b:Blush {nombre: $nombre})
        RETURN b
    """)
    Optional<Blush> findByNombre(@Param("nombre") String nombre);
}
