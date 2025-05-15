package com.ProyectoMaquillaje.data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.service.BlushService;

public class BlushDataLoader {
    @Autowired
    private BlushService blushService;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        init();
    }

    public void init() {
        //luminoso - polvo/crema/liquido - rosado/rojo/morado 
        guardarBlushSiNoExiste("Maybelline Blush Glow Rosé", "Maybelline", "Rubor luminoso rosado en polvo para un look natural.", 
        50.99, "luminoso", "polvo", "rosado");

        guardarBlushSiNoExiste("Cream Blush Ruby", "NYX", "Rubor en crema rojo de acabado luminoso.", 
        30.00, "luminoso", "crema", "rojo");

        guardarBlushSiNoExiste("Liquid Shine Plum", "Rare Beauty", "Rubor líquido morado con brillo sutil.", 
        50.99, "luminoso", "liquido", "morado");

        guardarBlushSiNoExiste("Luminous Blush Berry", "L'Oréal Paris", "Polvo compacto con pigmento intenso morado.",
        110.99, "luminoso", "polvo", "morado");

        guardarBlushSiNoExiste("Glow Cheek Tint Pink", "NARS", "Rubor líquido rosado brillante, de textura ligera.",
        30.00, "luminoso", "liquido", "rosado");

        

        //mate- luminoso/mate - polvo/crema/liquido - rosado/rojo/naranja/morado

    }

    private void guardarBlushSiNoExiste(String nombre, String marca, String descripcion, 
                                   double precio, String acabado, String presentacion, String tonoBlush) {
        Optional<Blush> existente = blushService.buscarPorNombre(nombre);
        if (existente.isEmpty()) {
            Blush prod = new Blush();
            prod.setNombre(nombre);
            prod.setMarca(marca);
            prod.setDescripcion(descripcion);
            prod.setPrecio(precio);
            prod.setAcabado(acabado);
            prod.setPresentacion(presentacion);
            prod.setTonoBlush(tonoBlush);
            blushService.guardarBlush(prod);
        }
    }
}
