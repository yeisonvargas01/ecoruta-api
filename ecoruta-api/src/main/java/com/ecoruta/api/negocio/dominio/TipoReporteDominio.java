package com.ecoruta.api.negocio.dominio;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public final class TipoReporteDominio {

    private static final int LONGITUD_MINIMA_NOMBRE = 3;
    private static final int LONGITUD_MAXIMA_NOMBRE = 80;

    private static final int LONGITUD_MAXIMA_DESCRIPCION = 250;

    private final Long idTipoReporte;

    private final String nombreTipo;

    private final String descripcionTipo;

    private final boolean activo;

    public TipoReporteDominio(
            Long idTipoReporte,
            String nombreTipo,
            String descripcionTipo,
            boolean activo) {

        this.idTipoReporte =
                validarIdTipoReporte(
                        idTipoReporte
                );

        this.nombreTipo =
                validarNombreTipo(
                        nombreTipo
                );

        this.descripcionTipo =
                validarDescripcionTipo(
                        descripcionTipo
                );

        this.activo = activo;
    }

    private Long validarIdTipoReporte(
            Long idTipoReporte) {

        Long valorValidado =
                UtilObjeto.obtenerValorObligatorio(
                        idTipoReporte,
                        "El identificador del tipo de reporte"
                );

        if (valorValidado <= 0) {
            throw new IllegalArgumentException(
                    "El identificador del tipo de reporte "
                    + "debe ser mayor que cero"
            );
        }

        return valorValidado;
    }

    private String validarNombreTipo(
            String nombreTipo) {

        return UtilTexto
                .obtenerValorObligatorioConLongitud(
                        nombreTipo,
                        "El nombre del tipo de reporte",
                        LONGITUD_MINIMA_NOMBRE,
                        LONGITUD_MAXIMA_NOMBRE
                );
    }

    private String validarDescripcionTipo(
            String descripcionTipo) {

        return UtilTexto
                .obtenerValorOpcionalConLongitud(
                        descripcionTipo,
                        "La descripción del tipo de reporte",
                        LONGITUD_MAXIMA_DESCRIPCION
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

    public boolean isActivo() {
        return activo;
    }
}
