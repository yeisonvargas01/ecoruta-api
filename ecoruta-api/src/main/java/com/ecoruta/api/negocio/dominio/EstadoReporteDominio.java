package com.ecoruta.api.negocio.dominio;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public final class EstadoReporteDominio {

    private static final int LONGITUD_MINIMA_NOMBRE = 3;
    private static final int LONGITUD_MAXIMA_NOMBRE = 40;

    private static final int LONGITUD_MAXIMA_DESCRIPCION = 250;

    private final Long idEstado;

    private final String nombreEstado;

    private final String descripcionEstado;

    private final boolean activo;

    public EstadoReporteDominio(
            Long idEstado,
            String nombreEstado,
            String descripcionEstado,
            boolean activo) {

        this.idEstado =
                validarIdEstado(idEstado);

        this.nombreEstado =
                validarNombreEstado(nombreEstado);

        this.descripcionEstado =
                validarDescripcionEstado(
                        descripcionEstado
                );

        this.activo = activo;
    }

    private Long validarIdEstado(
            Long idEstado) {

        Long valorValidado =
                UtilObjeto.obtenerValorObligatorio(
                        idEstado,
                        "El identificador del estado"
                );

        if (valorValidado <= 0) {
            throw new IllegalArgumentException(
                    "El identificador del estado "
                    + "debe ser mayor que cero"
            );
        }

        return valorValidado;
    }

    private String validarNombreEstado(
            String nombreEstado) {

        return UtilTexto
                .obtenerValorObligatorioConLongitud(
                        nombreEstado,
                        "El nombre del estado",
                        LONGITUD_MINIMA_NOMBRE,
                        LONGITUD_MAXIMA_NOMBRE
                );
    }

    private String validarDescripcionEstado(
            String descripcionEstado) {

        return UtilTexto
                .obtenerValorOpcionalConLongitud(
                        descripcionEstado,
                        "La descripción del estado",
                        LONGITUD_MAXIMA_DESCRIPCION
                );
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public boolean isActivo() {
        return activo;
    }
}
