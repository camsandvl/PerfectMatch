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

    @Query("""
    MATCH (u:Usuario {nombre: $usuario}),
          (c:Concelear {nombre: $nombre, tonoDePiel: $tonoDePiel, acabado: $acabado, cobertura: $cobertura})
    MERGE (u)-[:PREFIERE_CONCELEAR]->(c)
    """)
    void crearRelacionPrefiereConcelear(
    @Param("usuario") String usuario,
    @Param("nombre") String nombre,
    @Param("tonoDePiel") String tonoDePiel,
    @Param("acabado") String acabado,
    @Param("cobertura") String cobertura
);

    @Query("MATCH (u:Usuario {nombre: $usuario}), (c:Concelear {nombre: $nombre}) " +
        "MERGE (u)-[r:RETROALIMENTO {gusto: $gusto, fecha: date()}]->(c)")
    void crearRetroalimentacionConcelear(String usuario, String nombre, boolean gusto);

    @Query("MATCH (a:Concelear {nombre: $nombre1}), (b:Concelear {nombre: $nombre2}) " +
        "MERGE (a)-[:SIMILAR_CONCELEAR]->(b)")
    void crearRelacionSimilarConcelear(String nombre1, String nombre2);

    @Query("MATCH (c:Concelear) WHERE NOT EXISTS { MATCH (:Usuario {nombre: $usuario})-[:RETROALIMENTO]->(c) } RETURN c")
    List<Concelear> encontrarConcelearSinRetroalimentacion(String usuario);

    @Query("""
    MATCH (u:Usuario {nombre: $usuario})-[:RETROALIMENTO {gusto: true}]->(c:Concelear)
    MATCH (c)-[:SIMILAR_CONCELEAR]->(sim:Concelear)
    WHERE NOT EXISTS {
    MATCH (u)-[:RETROALIMENTO]->(sim)
    }
    RETURN sim ORDER BY rand() LIMIT 1
    """)
    Concelear recomendarSimilarConcelear(String usuario);

    @Query("MATCH (c:Concelear) WHERE c.nombre <> $nombre AND c.tonoDePiel = $tonoDePiel RETURN c")
    List<Concelear> findSimilaresPorTono(String nombre, String tonoDePiel);



}
