package com.ecoruta.api.negocio.casouso;

import java.util.List;

import com.ecoruta.api.dto.tipo.TipoReporteRespuestaDTO;

public interface ConsultarTiposReporteCasoUso {

    List<TipoReporteRespuestaDTO> ejecutar();
}
