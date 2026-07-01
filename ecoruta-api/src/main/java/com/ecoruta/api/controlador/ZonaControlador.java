package com.ecoruta.api.controlador;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecoruta.api.dto.zona.ZonaRespuestaDTO;
import com.ecoruta.api.negocio.casouso.ConsultarZonasCasoUso;
import com.ecoruta.api.utilitario.UtilObjeto;

@RestController
@RequestMapping(
    path = "/api/v1/zonas",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class ZonaControlador {

    private final ConsultarZonasCasoUso consultarZonasCasoUso;

    public ZonaControlador(
            ConsultarZonasCasoUso consultarZonasCasoUso) {

        this.consultarZonasCasoUso =
                UtilObjeto.obtenerValorObligatorio(
                        consultarZonasCasoUso,
                        "El caso de uso para consultar zonas"
                );
    }

    @GetMapping
    public ResponseEntity<List<ZonaRespuestaDTO>>
            consultarZonas() {

        List<ZonaRespuestaDTO> zonas =
                consultarZonasCasoUso.ejecutar();

        return ResponseEntity.ok(zonas);
    }
}