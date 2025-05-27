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
        110.00, "luminoso", "polvo", "rosado");

        guardarBlushSiNoExiste("On the Glow", "Pixi", "Rubor hidratante en barra con ginseng, aloe vera y extractos de frutas, ideal para mejillas y labios.",
        70.00, "luminoso", "crema", "rojo");

        guardarBlushSiNoExiste("Camo Liquid Blush Mauve", "e.l.f. Cosmetics", "Rubor líquido en tono malva con acabado luminoso, fórmula ligera y vegana con aplicador de esponja.",
        70.00, "luminoso", "liquido", "morado");

        guardarBlushSiNoExiste("NARS Blush Sin", "NARS", "Rubor en polvo tono ciruela con acabado luminoso y destellos dorados, ideal para un look sofisticado.",
        120.00, "luminoso", "polvo", "morado");

        guardarBlushSiNoExiste("Afterglow Liquid Blush Brazen", "NARS", "Rubor líquido rosado brillante, de textura ligera.",
        130.00, "luminoso", "liquido", "rosado");

        guardarBlushSiNoExiste("Cheeks Out Freestyle Daiquiri Dip", "Fenty Beauty", "Rubor en crema rojo brillante, ideal para todos los días.",
        165.00, "luminoso", "crema", "rojo");

        guardarBlushSiNoExiste("Primer Infused Blush Always Crushing", "Elf", "Rubor rosado en polvo con acabado mate.",
        50.00, "mate", "polvo", "rosado");

        guardarBlushSiNoExiste("Cream Blush Merlot Moment", "Milani", "Rubor en crema mate con pigmento rojo profundo.",
        19.00, "mate", "crema", "rojo");

        guardarBlushSiNoExiste("Soft Pinch Liquid Blush Faith", "Rare Beauty", "Rubor líquido morado con acabado mate.", 
        150.00, "mate", "liquido", "morado");

        guardarBlushSiNoExiste("Infallible Fresh Wear Blush Legendary Berry", "L'Oréal Paris", "Polvo compacto con pigmento intenso morado.",
        110.99, "mate", "polvo", "morado");

        guardarBlushSiNoExiste("Lip and Cheek Werk", "Milk Makeup", "Rubor en crema multifuncional para mejillas y labios, en tono rosado con acabado mate suave.",
        125.00, "mate", "crema", "rosado");

        guardarBlushSiNoExiste("Superdewy Liquid Blush Totally Blushed", "Makeup Revolution", "Rubor líquido en tono rojo con acabado mate natural, fórmula ligera y modulable para un look fresco.",
        70.00, "mate", "liquido", "rojo");

        guardarBlushSiNoExiste("Cloud Crush Blurring Blush Golden Hour", "Too Faced", "Rubor rosado suave en polvo con efecto luminoso.",
        190.00, "luminoso", "polvo", "rosado");

        guardarBlushSiNoExiste("Soft Pinch Liquid Blush Grateful", "Rare Beauty", "Rubor líquido en tono rojo verdadero con acabado luminoso, fórmula ligera y modulable para un look radiante.",
        120.00, "luminoso", "liquido", "rojo");

        guardarBlushSiNoExiste("Chubby Stick Plumped Up Peony", "Clinique", "Rubor en crema tono morado para un look fresco.",
        190.00, "luminoso", "crema", "morado");

        guardarBlushSiNoExiste("Major Headlines Double Take Blush She Left Me on Red", "Patrick Ta", "Dúo de rubores en crema y polvo. El polvo tiene acabado mate en tono rosado vibrante, ideal para un look audaz y dimensional.",
        296.00, "mate", "polvo", "rojo");

        guardarBlushSiNoExiste("Major Headlines Double Take Blush She Left Me on Red", "Patrick Ta", "Dúo de rubores en crema y polvo. El polvo tiene acabado mate en tono rosado vibrante, ideal para un look audaz y dimensional.",
        296.00, "mate", "crema", "rojo");

        guardarBlushSiNoExiste("Liquid Blush Angel Face", "ColourPop", "Rubor líquido mate en tono rosado.",
        50.00, "mate", "liquido", "rosado");

        guardarBlushSiNoExiste("Cheeks Out Freestyle Drama Class", "Fenty Beauty", "Rubor en crema morado suave, ideal para todos los días.",
        165.00, "mate", "crema", "morado");


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
