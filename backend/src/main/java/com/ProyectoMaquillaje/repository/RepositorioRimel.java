package com.ProyectoMaquillaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.model.Respuestas;
import com.ProyectoMaquillaje.model.Rimel;

public interface RepositorioRimel extends Neo4jRepository<Rimel, String> {
    @Query("""
        MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas),
              (I:Rimel)
        WHERE
            r.tonoDePiel = I.tonoDePiel AND
            r.acabado = I.acabado
        RETURN I
    """)
    List<Rimel> findRimelRecomendados(@Param("nombreUsuario") String nombreUsuario);

    //solo en lo que no está la implementación de usuario
    @Query("""
        MATCH (r:Respuestas), (I:Rimel)
        WHERE
            I.tonoDePiel = r.tonoDePiel AND
            I.acabado = r.acabado AND
            I.presentacion = r.presentacion AND
            I.tonoBlush = r.tonoBlush AND
            I.presentacion = r.presentacion AND
            r IN $respuestas
        RETURN I
    """)
    List<Blush> findRimelRecomendados(@Param("respuestas") List<Respuestas> respuestas);

    @Query("""
        MATCH (I:Rimel)
        WHERE
            I.tonoDePiel = $tonoDePiel AND
            I.acabado = $acabado AND
            I.presentacion = $presentacion AND
            I.tonoBlush = $tonoBlush
        RETURN I
    """)
    List<Rimel> recomendarPorRespuestas(String tonoDePiel, String acabado);

    @Query("""
    MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas)
    MATCH (I:Rimel)
    WHERE
        I.tonoDePiel = r.tonoDePiel AND
        I.acabado = r.acabado AND
        I.presentacion = r.presentacion AND
        I.tonoBlush = r.tonoBlush
    RETURN I
    """)
    List<Blush> recomendarPorUsuario(String nombreUsuario);

    @Query("""
        MATCH (I:Rimel {nombre: $nombre})
        RETURN I
    """)
    Optional<Rimel> findByNombre(@Param("nombre") String nombre);
}
