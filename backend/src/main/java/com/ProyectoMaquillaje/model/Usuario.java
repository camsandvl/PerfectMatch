package com.ProyectoMaquillaje.model;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String password;

    // Relación con las respuestas
    @Relationship(type = "RESPONDIO")
    private List<Respuestas> respuestas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Respuestas> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuestas> respuestas) {
        this.respuestas = respuestas;
    }
}
