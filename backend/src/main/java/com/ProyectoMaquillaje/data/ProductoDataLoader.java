package com.ProyectoMaquillaje.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.service.ProductoService;

import java.util.Optional;

@Component
public class ProductoDataLoader {

    @Autowired
    private ProductoService productoService;

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
        Optional<Producto> existente = productoService.buscarPorNombre(nombre);
        if (existente.isEmpty()) {
            Producto prod = new Producto();
            prod.setNombre(nombre);
            prod.setMarca(marca);
            prod.setDescripcion(descripcion);
            prod.setPrecio(precio);
            prod.setTonoDePiel(tonoDePiel);
            prod.setAcabado(acabado);
            prod.setCobertura(cobertura);
            productoService.guardarProducto(prod);
        }
    }
}
