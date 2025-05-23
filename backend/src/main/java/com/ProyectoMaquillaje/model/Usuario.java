package com.ProyectoMaquillaje.model;

import java.util.ArrayList;
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

    @Relationship(type = "PREFIERE", direction = Relationship.Direction.OUTGOING)
    private List<Concelear> corrector = new ArrayList<>();

     @Relationship(type = "PREFIERE_BLUSH", direction = Relationship.Direction.OUTGOING)
    private List<Blush> blushes = new ArrayList<>();

    @Relationship(type = "PREFIERE_RIMEL", direction = Relationship.Direction.OUTGOING)
    private List<Rimel> rimels = new ArrayList<>();

    public List<Blush> getBlushes() { return blushes; }
    public void setBlushes(List<Blush> blushes) { this.blushes = blushes; }
    public void addBlush(Blush blush) { this.blushes.add(blush); }

    public List<Rimel> getRimels() { return rimels; }
    public void setRimels(List<Rimel> rimels) { this.rimels = rimels; }
    public void addRimel(Rimel rimel) { this.rimels.add(rimel); }

    @Relationship(type = "RESPONDIO", direction = Relationship.Direction.OUTGOING)
    private List<Respuestas> respuestas = new ArrayList<>();


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

    public List<Concelear> getCorrector() {
        return corrector;
    }

    public void setCorrector(List<Concelear> corrector) {
        this.corrector = corrector;
    }

    public void addRespuesta(Respuestas respuesta) {
        respuesta.setUsuario(this);  // Establece la relación inversa (muy importante)
        this.respuestas.add(respuesta);
    }

    public void addCorrector(Concelear corrector) {
        this.corrector.add(corrector);
    }

}
