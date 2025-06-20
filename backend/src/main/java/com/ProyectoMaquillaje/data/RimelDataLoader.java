package com.ProyectoMaquillaje.data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ProyectoMaquillaje.model.Rimel;
import com.ProyectoMaquillaje.service.RimelService;

@Component
public class RimelDataLoader {
    @Autowired
    private RimelService rimelService;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        init();
    }

    public void init() {
        guardarRimelSiNoExiste("Voluminous Lash Black WP", "L'Oréal Paris", "Máscara negra a prueba de agua que da volumen intenso.",
        190.00, "negro", true, "dar volumen");

        guardarRimelSiNoExiste("Voluminous Lash Black", "L'Oréal Paris", "Máscara negra no waterproof para volumen suave y flexible.",
        190.00, "negro", false, "dar volumen");

        guardarRimelSiNoExiste("Sky High Length Black WP", "Maybelline", "Rímel negro waterproof para alargar hasta la punta.",
        120.00, "negro", true, "alargar");

        guardarRimelSiNoExiste("Sky High Length Black", "Maybelline", "Rímel negro alargador, fácil de retirar con agua.",
        120.00, "negro", false, "alargar");

        guardarRimelSiNoExiste("Volume Boost Blue WP", "NYX", "Máscara azul resistente al agua para volumen dramático.",
        90.00, "azul", true, "dar volumen");

        guardarRimelSiNoExiste("Volume Boost Blue", "NYX", "Máscara azul no waterproof para look vibrante con volumen.",
        90.00, "azul", false, "dar volumen");

        guardarRimelSiNoExiste("Length Maker Blue WP", "CoverGirl", "Rímel azul a prueba de agua para pestañas más largas.",
        150.00, "azul", true, "alargar");

        guardarRimelSiNoExiste("Length Maker Blue", "CoverGirl", "Fórmula alargadora azul para destacar la mirada.",
        125.00, "azul", false, "alargar");

        guardarRimelSiNoExiste("Clear Lash Fix WP", "Essence", "Máscara transparente waterproof para fijación con efecto natural.",
        70.00, "transparente", true, "dar volumen");

        guardarRimelSiNoExiste("Clear Lash Fix", "Essence", "Gel de pestañas transparente no waterproof para look fresco.",
        70.00, "transparente", false, "dar volumen");

        guardarRimelSiNoExiste("Natural Length Clear WP", "e.l.f. Cosmetics", "Gel transparente resistente al agua que alarga sutilmente.",
        90.00, "transparente", true, "alargar");

        guardarRimelSiNoExiste("Natural Length Clear", "Maybelline", "Gel transparente para alargar de forma natural, sin grumos.",
        90.00, "transparente", false, "alargar");
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
