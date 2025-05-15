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
            //tonos de piel claros
        guardarCorrectorSiNoExiste("Fit Me Concealer", "Maybelline", 
            "Corrector de ojeras con acabado luminoso y cobertura media para tonos claros.", 
            49.00, "claro", "luminoso", "media");
        
        guardarCorrectorSiNoExiste("Too Faced Born This Way", "Too Faced",
            "Corrector de ojeras con acabado luminoso y cobertura alta para tonos claros.",
            150.00, "claro", "luminoso", "alta");
        
        guardarCorrectorSiNoExiste("Elf 16HR camo", "Elf", 
            "Corrector de ojeras con acabado mate y cobertura media para tonos claros.", 
            45.00, "claro", "mate", "media");
    
        guardarCorrectorSiNoExiste("Wet n Wild Photo Focus", "Wet n Wild",
            "Corrector de ojeras con acabado mate y alta cobertura para tonos claros.",
            29.00, "claro", "mate", "alta");

            //tono de piel medio
        guardarCorrectorSiNoExiste("Radiant Creamy Concealer", "NARS", 
            "Corrector de ojeras con acabado luminoso y alta cobertura para tonos medios.", 
            32.00, "medio", "luminoso", "alta");
        
        guardarCorrectorSiNoExiste("Revlon ColorStay", "Revlon",
            "Corrector de ojeras con acabado luminoso y cobertura media para tonos medios.",
            59.00, "medio", "luminoso", "media");
        
         guardarCorrectorSiNoExiste("MAC Pro Longwear", "MAC Cosmetics", 
            "Corrector de ojeras con acabado mate y alta cobertura para tonos medios.", 
            165.00, "medio", "mate", "media");

        guardarCorrectorSiNoExiste("Covergirl TruBlend Undercover", "Covergirl",
            "Corrector de ojeras con acabado mate y cobertura alta para tonos medios.",
            59.00, "medio", "mate", "alta");
        
            //tono de piel oscuro
        guardarCorrectorSiNoExiste("LA girl pro conceal", "L.A. Girl Cosmetics", 
            "Corrector de ojeras con acabado luminoso y alta cobertura para tonos oscuros.", 
            32.00, "oscuro", "luminoso", "alta");

        guardarCorrectorSiNoExiste("Fenty Beauty Bright Fix", "Fenty Beauty",
            "Corrector de ojeras con acabado luminoso y cobertura media para tonos oscuros.",
            180.00, "oscuro", "luminoso", "media");
        
        guardarCorrectorSiNoExiste("Hourglass Hidden Corrective", "Hourglass",
            "Corrector de ojeras con acabado mate y cobertura media para tonos oscuros.",
            49.00, "oscuro", "mate", "alta");
        
         guardarCorrectorSiNoExiste("Tarte Shape Tape", "Tarte", 
            "Corrector de ojeras con acabado mate y alta cobertura para tonos oscuros.", 
            170.00, "oscuro", "mate", "media");
    }

    //cargar productos a la base de datos solo si no existen para evitar duplicados
    //se utiliza Optional para verificar si el producto ya existe

    private void guardarCorrectorSiNoExiste(String nombre, String marca, String descripcion, 
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
