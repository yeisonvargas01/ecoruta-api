package com.ecoruta.api.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecoruta.api.dto.reporte.CrearReporteDTO;
import com.ecoruta.api.dto.reporte.ReporteRespuestaDTO;
import com.ecoruta.api.negocio.casouso.RegistrarReporteCasoUso;
import com.ecoruta.api.utilitario.UtilObjeto;

import jakarta.validation.Valid;

@RestController
@RequestMapping(
    path = "/api/v1/reportes",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class ReporteControlador {

    private final RegistrarReporteCasoUso
            registrarReporteCasoUso;

    public ReporteControlador(
            RegistrarReporteCasoUso
                    registrarReporteCasoUso) {

        this.registrarReporteCasoUso =
                UtilObjeto.obtenerValorObligatorio(
                        registrarReporteCasoUso,
                        "El caso de uso para registrar reportes"
                );
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ReporteRespuestaDTO>
            registrarReporte(
                    @Valid
                    @RequestBody
                    CrearReporteDTO datos) {

        ReporteRespuestaDTO respuesta =
                registrarReporteCasoUso.ejecutar(
                        datos
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }
}