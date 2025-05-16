package com.ProyectoMaquillaje.data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ProyectoMaquillaje.model.Blush;
import com.ProyectoMaquillaje.service.BlushService;

@Component
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

        guardarBlushSiNoExiste("Radiant Red Cream", "Fenty Beauty", "Rubor en crema rojo brillante, ideal para todos los días.",
        90.00, "luminoso", "crema", "rojo");

        guardarBlushSiNoExiste("Matte Touch Rosé", "Elf", "Rubor rosado en polvo con acabado mate.",
        60.00, "mate", "polvo", "rosado");

        guardarBlushSiNoExiste("Velvet Cream Blush Red", "Milani", "Rubor en crema mate con pigmento rojo profundo.",
        19.00, "mate", "crema", "rojo");

        guardarBlushSiNoExiste("Soft Matte Liquid Violet", "Tarte", "Rubor líquido morado mate, ideal para un acabado elegante.",
        28.00, "mate", "liquido", "morado");

        guardarBlushSiNoExiste("Matte finish blush Magenta", "MAC", "Polvo compacto mate en tono morado vibrante.",
        27.00, "mate", "polvo", "morado");

        guardarBlushSiNoExiste("Pure Pink Cream Blush", "Benefit", "Rubor en crema mate de tono rosado pastel.",
        19.00, "mate", "crema", "rosado");

        guardarBlushSiNoExiste("Matte Liquid Blush Crimson", "Revolution", "Rubor líquido mate en tono rojo fuerte.",
        9.99, "mate", "liquido", "rojo");

        guardarBlushSiNoExiste("Soft Luminous Pink Powder", "Too Faced", "Rubor rosado suave en polvo con efecto luminoso.",
        9.99, "luminoso", "polvo", "rosado");

        guardarBlushSiNoExiste("Liquid Love Red Glow", "KIKO Milano", "Rubor líquido rojo con brillantes",
        19.00, "luminoso", "liquido", "rojo");

        guardarBlushSiNoExiste("Creamy Plum Pop", "Clinique", "Rubor en crema tono morado para un look fresco.",
        19.00, "luminoso", "crema", "morado");

        guardarBlushSiNoExiste("Matte Blush Red Earth", "Glossier", "Rubor rojo mate en polvo para un acabado natural.",
        18.00, "mate", "polvo", "rojo");

        guardarBlushSiNoExiste("Pink Matte Cheek Tint", "ColourPop", "Rubor líquido mate en tono rosado.",
        12.00, "mate", "liquido", "rosado");

        guardarBlushSiNoExiste("Berry Blush Cream Stick", "Revlon", "RRubor en crema mate tono morado para contorno sutil.",
        8.99, "mate", "crema", "morado");


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
