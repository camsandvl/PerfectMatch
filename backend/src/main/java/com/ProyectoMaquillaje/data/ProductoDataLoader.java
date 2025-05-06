package com.ProyectoMaquillaje.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ProyectoMaquillaje.model.Producto;
import com.ProyectoMaquillaje.service.ProductoService;

@Component
public class ProductoDataLoader {

    @Autowired
    private ProductoService productoService;
    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        init();
    }

    public void init() {
        Producto prod1 = new Producto();
        prod1.setNombre("Concelear Mate");
        prod1.setDescripcion("Corrector de ojeras con acabado mate y alta cobertura.");
       
        Producto prod2 = new Producto();
        prod2.setNombre("Concelear Hidratante");
        prod2.setDescripcion("Corrector de ojeras con acabado hidratante y cobertura ligera.");
        

        productoService.guardarProducto(prod1);
        productoService.guardarProducto(prod2);
    }
}
