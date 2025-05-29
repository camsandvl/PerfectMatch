package com.ProyectoMaquillaje.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.ProyectoMaquillaje.model.Rimel;

public interface RepositorioRimel extends Neo4jRepository<Rimel, Long> {
    @Query("""
        MATCH (u:Usuario {nombre: $nombreUsuario})-[:RESPONDIO]->(r:Respuestas)
        MATCH (I:Rimel)
        WHERE
            I.color = r.color AND
            I.waterproof = r.waterproof AND
            I.funcion = r.funcion
        RETURN I
    """)
    List<Rimel> recomendarPorUsuario(@Param("nombreUsuario") String nombreUsuario);

    @Query("""
        MATCH (I:Rimel)
        WHERE
            I.color = $color AND
            I.waterproof = $waterproof AND
            I.funcion = $funcion
        RETURN I
    """)
    List<Rimel> recomendarPorRespuestas(@Param("color") String color,
                                        @Param("waterproof") boolean waterproof,
                                        @Param("funcion") String funcion);

    @Query("""
        MATCH (I:Rimel {nombre: $nombre})
        RETURN I
    """)
    Optional<Rimel> findByNombre(@Param("nombre") String nombre);

    @Query("""
MATCH (u:Usuario {nombre: $usuario}),
      (R:Rimel {nombre: $nombreRimel, color: $color, waterproof: $waterproof, funcion: $funcion})
MERGE (u)-[:PREFIERE_RIMEL]->(R)
""")
void crearRelacionPrefiereRimel(
    @Param("usuario") String usuario,
    @Param("nombreRimel") String nombreRimel,
    @Param("color") String color,
    @Param("waterproof") boolean waterproof,
    @Param("funcion") String funcion
);

// Crear retroalimentación
@Query("MATCH (u:Usuario {nombre: $usuario}), (r:Rimel {nombre: $nombre}) " +
       "MERGE (u)-[rel:RETROALIMENTO {gusto: $gusto, fecha: date()}]->(r)")
void crearRetroalimentacionRimel(String usuario, String nombre, boolean gusto);

// Crear relación SIMILAR_RIMEL
@Query("MATCH (a:Rimel {nombre: $nombre1}), (b:Rimel {nombre: $nombre2}) " +
       "MERGE (a)-[:SIMILAR_RIMEL]->(b)")
void crearRelacionSimilarRimel(String nombre1, String nombre2);

// Encontrar rimels sin retroalimentación
@Query("MATCH (r:Rimel) WHERE NOT EXISTS { MATCH (:Usuario {nombre: $usuario})-[:RETROALIMENTO]->(r) } RETURN r")
List<Rimel> encontrarRimelSinRetroalimentacion(String usuario);

// Recomendar rimel similar
@Query("""
MATCH (u:Usuario {nombre: $usuario})-[:RETROALIMENTO {gusto: true}]->(r:Rimel)
MATCH (r)-[:SIMILAR_RIMEL]->(sim:Rimel)
WHERE NOT EXISTS {
  MATCH (u)-[:RETROALIMENTO]->(sim)
}
RETURN sim ORDER BY rand() LIMIT 1
""")
Rimel recomendarSimilarRimel(String usuario);

// Buscar rimels similares por color, waterproof y funcion
@Query("MATCH (r:Rimel) WHERE r.nombre <> $nombre AND r.color = $color AND r.funcion = $funcion RETURN r")
List<Rimel> findSimilares(String nombre, String color, String funcion);
}
