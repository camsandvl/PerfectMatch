package com.ProyectoMaquillaje.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Respuestas")
public class Respuestas {

    @Id
    @GeneratedValue
    private Long id;

    private String tonoDePiel;
    private String acabado;
    private String cobertura;

    @Relationship(type = "RESPONDIO", direction = Relationship.Direction.OUTGOING)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTonoDePiel() {
        return tonoDePiel;
    }

    public void setTonoDePiel(String tonoDePiel) {
        this.tonoDePiel = tonoDePiel;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
