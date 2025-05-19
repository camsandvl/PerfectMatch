package com.ProyectoMaquillaje.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Node ("Blush")
public class Blush {
    @JsonIgnore
    private Usuario usuario;
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String marca;
    private String descripcion;
    private double precio;
    private String acabado;
    private String presentacion; // polvo, crema, liquido
    private String tonoBlush;

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
    }

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAcabado() {
        return acabado;
    }
    
    public void setAcabado(String acabado) {
        this.acabado = acabado;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getTonoBlush() {
        return tonoBlush;
    }

    public void setTonoBlush(String tonoBlush) {
        this.tonoBlush = tonoBlush;
    }

}
