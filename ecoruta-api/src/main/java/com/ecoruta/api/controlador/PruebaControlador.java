package com.ecoruta.api.controlador;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaControlador {

    @GetMapping("/api/prueba")
    public Map<String, String> probarApi() {
        return Map.of(
            "estado", "OK",
            "mensaje", "EcoRuta API funcionando correctamente"
        );
    }
}
