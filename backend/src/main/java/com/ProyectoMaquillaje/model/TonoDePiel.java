package com.ProyectoMaquillaje.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("TonoDePiel")
public class TonoDePiel {

    @Id
    @GeneratedValue
    private Long id;

    // Claro, Medio, Oscuro
    private String tono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTono() {
        return tono;
    }

    public void setTono(String tono) {
        this.tono = tono;
    }
}
