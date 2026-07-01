package com.ecoruta.api.negocio.casouso;

import com.ecoruta.api.dto.reporte.CrearReporteDTO;
import com.ecoruta.api.dto.reporte.ReporteRespuestaDTO;

public interface RegistrarReporteCasoUso {

    ReporteRespuestaDTO ejecutar(
            CrearReporteDTO datos
    );
}
