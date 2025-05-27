package com.ProyectoMaquillaje.repository;

import java.util.List;
import java.util.Map;
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




}
