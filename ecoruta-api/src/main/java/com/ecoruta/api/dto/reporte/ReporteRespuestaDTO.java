package com.ecoruta.api.dto.reporte;

import java.time.OffsetDateTime;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public final class ReporteRespuestaDTO {

    private final Long idReporte;

    private final String nombreCiudadano;

    private final Long idZona;

    private final String nombreZona;

    private final Long idTipoReporte;

    private final String nombreTipoReporte;

    private final Long idEstadoReporte;

    private final String nombreEstadoReporte;

    private final String descripcion;

    private final String direccionReferencia;

    private final String evidenciaUrl;

    private final String observacionRespuesta;

    private final OffsetDateTime fechaReporte;

    public ReporteRespuestaDTO(
            Long idReporte,
            String nombreCiudadano,
            Long idZona,
            String nombreZona,
            Long idTipoReporte,
            String nombreTipoReporte,
            Long idEstadoReporte,
            String nombreEstadoReporte,
            String descripcion,
            String direccionReferencia,
            String evidenciaUrl,
            String observacionRespuesta,
            OffsetDateTime fechaReporte) {

        this.idReporte =
                UtilObjeto.obtenerValorObligatorio(
                    idReporte,
                    "El identificador del reporte"
                );

        this.nombreCiudadano =
                UtilTexto.obtenerValorObligatorio(
                    nombreCiudadano,
                    "El nombre del ciudadano"
                );

        this.idZona =
                UtilObjeto.obtenerValorObligatorio(
                    idZona,
                    "El identificador de la zona"
                );

        this.nombreZona =
                UtilTexto.obtenerValorObligatorio(
                    nombreZona,
                    "El nombre de la zona"
                );

        this.idTipoReporte =
                UtilObjeto.obtenerValorObligatorio(
                    idTipoReporte,
                    "El identificador del tipo de reporte"
                );

        this.nombreTipoReporte =
                UtilTexto.obtenerValorObligatorio(
                    nombreTipoReporte,
                    "El nombre del tipo de reporte"
                );

        this.idEstadoReporte =
                UtilObjeto.obtenerValorObligatorio(
                    idEstadoReporte,
                    "El identificador del estado"
                );

        this.nombreEstadoReporte =
                UtilTexto.obtenerValorObligatorio(
                    nombreEstadoReporte,
                    "El nombre del estado"
                );

        this.descripcion =
                UtilTexto.obtenerValorObligatorio(
                    descripcion,
                    "La descripción del reporte"
                );

        this.direccionReferencia =
                UtilTexto.obtenerValorOpcional(
                    direccionReferencia
                );

        this.evidenciaUrl =
                UtilTexto.obtenerValorOpcional(
                    evidenciaUrl
                );

        this.observacionRespuesta =
                UtilTexto.obtenerValorOpcional(
                    observacionRespuesta
                );

        this.fechaReporte =
                UtilObjeto.obtenerValorObligatorio(
                    fechaReporte,
                    "La fecha del reporte"
                );
    }

    public Long getIdReporte() {
        return idReporte;
    }

    public String getNombreCiudadano() {
        return nombreCiudadano;
    }

    public Long getIdZona() {
        return idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public Long getIdTipoReporte() {
        return idTipoReporte;
    }

    public String getNombreTipoReporte() {
        return nombreTipoReporte;
    }

    public Long getIdEstadoReporte() {
        return idEstadoReporte;
    }

    public String getNombreEstadoReporte() {
        return nombreEstadoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccionReferencia() {
        return direccionReferencia;
    }

    public String getEvidenciaUrl() {
        return evidenciaUrl;
    }

    public String getObservacionRespuesta() {
        return observacionRespuesta;
    }

    public OffsetDateTime getFechaReporte() {
        return fechaReporte;
    }
}
