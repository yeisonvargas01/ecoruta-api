package com.ecoruta.api.controlador;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecoruta.api.dto.tipo.TipoReporteRespuestaDTO;
import com.ecoruta.api.negocio.casouso.ConsultarTiposReporteCasoUso;
import com.ecoruta.api.utilitario.UtilObjeto;

@RestController
@RequestMapping(
    path = "/api/v1/tipos-reporte",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class TipoReporteControlador {

    private final ConsultarTiposReporteCasoUso
            consultarTiposReporteCasoUso;

    public TipoReporteControlador(
            ConsultarTiposReporteCasoUso
                    consultarTiposReporteCasoUso) {

        this.consultarTiposReporteCasoUso =
                UtilObjeto.obtenerValorObligatorio(
                        consultarTiposReporteCasoUso,
                        "El caso de uso para consultar "
                        + "los tipos de reporte"
                );
    }

    @GetMapping
    public ResponseEntity<List<TipoReporteRespuestaDTO>>
            consultarTiposReporte() {

        List<TipoReporteRespuestaDTO> tiposReporte =
                consultarTiposReporteCasoUso.ejecutar();

        return ResponseEntity.ok(tiposReporte);
    }
}
