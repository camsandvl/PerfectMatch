package com.ProyectoMaquillaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.ProyectoMaquillaje.model.Concelear;

public interface RepositorioConcelear extends Neo4jRepository<Concelear, Long> {

    @Query("""
    MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas)
    MATCH (p:Concelear)
    WHERE 
        p.tonoDePiel = r.tonoDePiel AND
        p.acabado = r.acabado AND
        p.cobertura = r.cobertura
    RETURN p
""")
    List<Concelear> findCorrectoresRecomendados(@Param("nombreUsuario") String nombreUsuario);

    @Query("""
    MATCH (u:Usuario {nombre: $nombreUsuario}), (p:Concelear {nombre: $nombreConcelear})
    MERGE (u)-[:PREFIERE]->(p)
    """)
    void crearRelacionPrefiere(@Param("nombreUsuario") String nombreUsuario, @Param("nombreConcelear") String nombreConcelear);

    //solo en lo que no está la implementación de usuario
    @Query("""
    MATCH (c:Concelear)
    WHERE
        c.tonoDePiel = $tonoDePiel AND
        $acabado IN c.acabado AND
        c.cobertura = $cobertura
    RETURN c
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
