package com.ecoruta.api.dto.tipo;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public final class TipoReporteRespuestaDTO {

    private final Long idTipoReporte;

    private final String nombreTipo;

    private final String descripcionTipo;

    public TipoReporteRespuestaDTO(
            Long idTipoReporte,
            String nombreTipo,
            String descripcionTipo) {

        this.idTipoReporte =
                UtilObjeto.obtenerValorObligatorio(
                    idTipoReporte,
                    "El identificador del tipo de reporte"
                );

        this.nombreTipo =
                UtilTexto.obtenerValorObligatorio(
                    nombreTipo,
                    "El nombre del tipo de reporte"
                );

        this.descripcionTipo =
                UtilTexto.obtenerValorOpcional(
                    descripcionTipo
                );
    }

    public Long getIdTipoReporte() {
        return idTipoReporte;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }
}
