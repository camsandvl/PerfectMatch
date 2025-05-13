package com.ProyectoMaquillaje.data;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ProyectoMaquillaje.model.Concelear;
import com.ProyectoMaquillaje.service.ConcelearService;

@Component
public class ConcelearDataLoader {

    @Autowired
    private ConcelearService concelearService;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        init();
    }

    public void init() {
        guardarSiNoExiste("Fit Me Concealer", "Maybelline", 
            "Corrector de ojeras con acabado luminoso y cobertura media.", 
            10.99, "claro", "luminoso", "media");

        guardarSiNoExiste("Radiant Creamy Concealer", "NARS", 
            "Corrector de ojeras con acabado luminoso y alta cobertura.", 
            29.99, "medio", "luminoso", "alta");

        guardarSiNoExiste("Tarte Shape Tape", "Tarte", 
            "Corrector de ojeras con acabado mate y alta cobertura.", 
            27.00, "oscuro", "mate", "alta");
    }

    private void guardarSiNoExiste(String nombre, String marca, String descripcion, 
                                   double precio, String tonoDePiel, 
                                   String acabado, String cobertura) {
        Optional<Concelear> existente = concelearService.buscarPorNombre(nombre);
        if (existente.isEmpty()) {
            Concelear prod = new Concelear();
            prod.setNombre(nombre);
            prod.setMarca(marca);
            prod.setDescripcion(descripcion);
            prod.setPrecio(precio);
            prod.setTonoDePiel(tonoDePiel);
            prod.setAcabado(acabado);
            prod.setCobertura(cobertura);
            concelearService.guardarCorrector(prod);
        }
    }
}
