package com.ProyectoMaquillaje.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Preferencia")
public class Preferencia {

    @Id
    @GeneratedValue
    private Long id;

    // Acabado mate, Hidratante, Larga duración
    private String acabado;

    // Alta cobertura, Cobertura ligera
    private String cobertura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcabado() {
        return acabado;
    }

    public void setAcabado(String acabado) {
        this.acabado = acabado;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }
}
