package com.ProyectoMaquillaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.model.Respuestas;

public interface RepositorioConcelear extends Neo4jRepository<Concelear, Long> {

    @Query("""
        MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas),
              (p:Concelear)-[:TIENE_TONO]->(t:TonoDePiel),
              (p)-[:TIENE_ACABADO]->(a:Acabado),
              (p)-[:TIENE_COBERTURA]->(c:Cobertura)
        WHERE
            r.tonoDePiel = t.nombre AND
            r.acabado = a.nombre AND
            r.cobertura = c.nombre
        RETURN p
    """)
    List<Concelear> findCorrectoresRecomendados(@Param("nombreUsuario") String nombreUsuario);

    @Query("""
        MATCH (r:Respuestas),
              (p:Concelear)-[:TIENE_TONO]->(t:TonoDePiel),
              (p)-[:TIENE_ACABADO]->(a:Acabado),
              (p)-[:TIENE_COBERTURA]->(c:Cobertura)
        WHERE
            r.tonoDePiel = t.nombre AND
            r.acabado = a.nombre AND
            r.cobertura = c.nombre
            AND r IN $respuestas
        RETURN p
    """)
    List<Concelear> findCorrectoresRecomendados(@Param("respuestas") List<Respuestas> respuestas);

    @Query("""
    MATCH (p:Concelear)
    WHERE
        p.tonoDePiel = $tonoDePiel AND
        $acabado IN p.acabado AND
        p.cobertura = $cobertura
    RETURN p
    """)
    List<Concelear> recomendarPorRespuestas(String tonoDePiel, String acabado, String cobertura);

    @Query("""
    MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas)
    MATCH (p:Concelear)
    WHERE 
        p.tonoDePiel = r.tonoDePiel AND
        p.acabado = r.acabado AND
        p.cobertura = r.cobertura
    RETURN p
    """)
List<Concelear> recomendarPorUsuario(String nombreUsuario);
    @Query("""
        MATCH (p:Concelear {nombre: $nombre})
        RETURN p
    """)
    Optional<Concelear> findByNombre(@Param("nombre") String nombre);



}
