package com.ProyectoMaquillaje.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node("Concelear")
public class Concelear {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String marca;
    private String tonoDePiel;
    private String descripcion;
    private double precio;
    private String acabado;
    private String cobertura;

    
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

    public String getTonoDePiel() {
        return tonoDePiel;
    }

    public void setTonoDePiel(String tono) {
        this.tonoDePiel = tono;
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
    
    public String getCobertura() {
        return cobertura;
    }
    
    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }
    
}
