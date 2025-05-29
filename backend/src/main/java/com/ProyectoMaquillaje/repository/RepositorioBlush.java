package com.ProyectoMaquillaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.ProyectoMaquillaje.model.Blush;

public interface RepositorioBlush extends Neo4jRepository<Blush, Long> {

    @Query("""
        MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas)
        MATCH (b:Blush)
        WHERE
            b.acabado = r.acabado AND
            b.presentacion = r.presentacion AND
            b.tonoBlush = r.tonoBlush
        RETURN b
    """)
    List<Blush> recomendarPorUsuario(@Param("nombreUsuario") String nombreUsuario);

    @Query("""
        MATCH (b:Blush)
        WHERE
            b.acabado = $acabado AND
            b.presentacion = $presentacion AND
            b.tonoBlush = $tonoBlush
        RETURN b
    """)
    List<Blush> recomendarPorRespuestas(@Param("acabado") String acabado,
                                        @Param("presentacion") String presentacion,
                                        @Param("tonoBlush") String tonoBlush);

    @Query("""
        MATCH (b:Blush {nombre: $nombre})
        RETURN b
    """)
    Optional<Blush> findByNombre(@Param("nombre") String nombre);

    @Query("""
    MATCH (u:Usuario {nombre: $usuario}),
          (b:Blush {nombre: $nombreBlush, presentacion: $presentacion, acabado: $acabado, tonoBlush: $tonoBlush})
    MERGE (u)-[:PREFIERE_BLUSH]->(b)
""")
void crearRelacionPrefiereBlush(
    @Param("usuario") String usuario,
    @Param("nombreBlush") String nombreBlush,
    @Param("presentacion") String presentacion,
    @Param("acabado") String acabado,
    @Param("tonoBlush") String tonoBlush
);

// Crear retroalimentación
@Query("MATCH (u:Usuario {nombre: $usuario}), (b:Blush {nombre: $nombre}) " +
       "MERGE (u)-[r:RETROALIMENTO {gusto: $gusto, fecha: date()}]->(b)")
void crearRetroalimentacionBlush(String usuario, String nombre, boolean gusto);

// Crear relación SIMILAR_BLUSH
@Query("MATCH (a:Blush {nombre: $nombre1}), (b:Blush {nombre: $nombre2}) " +
       "MERGE (a)-[:SIMILAR_BLUSH]->(b)")
void crearRelacionSimilarBlush(String nombre1, String nombre2);

// Encontrar blushes sin retroalimentación
@Query("MATCH (b:Blush) WHERE NOT EXISTS { MATCH (:Usuario {nombre: $usuario})-[:RETROALIMENTO]->(b) } RETURN b")
List<Blush> encontrarBlushSinRetroalimentacion(String usuario);

// Recomendar blush similar
@Query("""
MATCH (u:Usuario {nombre: $usuario})-[:RETROALIMENTO {gusto: true}]->(b:Blush)
MATCH (b)-[:SIMILAR_BLUSH]->(sim:Blush)
WHERE NOT EXISTS {
  MATCH (u)-[:RETROALIMENTO]->(sim)
}
RETURN sim ORDER BY rand() LIMIT 1
""")
Blush recomendarSimilarBlush(String usuario);

// Buscar blushes similares por acabado, presentacion y tonoBlush
@Query("MATCH (b:Blush) WHERE b.nombre <> $nombre AND b.acabado = $acabado AND b.presentacion = $presentacion AND b.tonoBlush = $tonoBlush RETURN b")
List<Blush> findSimilares(String nombre, String acabado, String presentacion, String tonoBlush);




}
