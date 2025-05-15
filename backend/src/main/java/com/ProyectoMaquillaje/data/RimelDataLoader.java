package com.ProyectoMaquillaje.data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.service.RimelService;

public class RimelDataLoader {
    @Autowired
    private RimelService rimelService;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        init();
    }

    public void init() {
        //color negro
        //negro - waterproof/no - alargar/volumen
        guardarRimelSiNoExiste(nombre, marca, descripcion, precio, color, waterproof, funcion);

        //color azul
        //azul- waterproof/no - alargar/volumen

        //color transparante
        //transparente - waterproof/no - alargar/volumen
    }

    private void guardarRimelSiNoExiste(String nombre, String marca, String descripcion,
                                   double precio, String color, boolean waterproof, String funcion) {
        Optional<Rimel> existente = rimelService.buscarPorNombre(nombre);
        if (existente.isEmpty()) {
            Rimel prod = new Rimel();
            prod.setNombre(nombre);
            prod.setMarca(marca);
            prod.setDescripcion(descripcion);
            prod.setPrecio(precio);
            prod.setColor(color);
            prod.setWaterproof(waterproof);
            prod.setFuncion(funcion);
            rimelService.guardarRimel(prod);
        }
    }
}