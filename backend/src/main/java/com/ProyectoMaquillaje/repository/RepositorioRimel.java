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
}
