package com.ecoruta.api.negocio.dominio;

import java.time.OffsetDateTime;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public final class ReporteDominio {

    private static final int LONGITUD_MINIMA_DESCRIPCION = 10;
    private static final int LONGITUD_MAXIMA_DESCRIPCION = 1000;

    private static final int LONGITUD_MAXIMA_DIRECCION = 200;
    private static final int LONGITUD_MAXIMA_EVIDENCIA = 500;

    private final Long idReporte;

    private final CiudadanoDominio ciudadano;

    private final ZonaDominio zona;

    private final EstadoReporteDominio estadoReporte;

    private final TipoReporteDominio tipoReporte;

    private final String descripcion;

    private final String direccionReferencia;

    private final String evidenciaUrl;

    private final String observacionRespuesta;

    private final OffsetDateTime fechaReporte;

    private ReporteDominio(
            Long idReporte,
            CiudadanoDominio ciudadano,
            ZonaDominio zona,
            EstadoReporteDominio estadoReporte,
            TipoReporteDominio tipoReporte,
            String descripcion,
            String direccionReferencia,
            String evidenciaUrl,
            String observacionRespuesta,
            OffsetDateTime fechaReporte) {

        validarConsistenciaPersistencia(
                idReporte,
                fechaReporte
        );

        this.idReporte = idReporte;

        this.ciudadano =
                validarCiudadano(ciudadano);

        this.zona =
                validarZona(zona);

        this.estadoReporte =
                validarEstadoReporte(
                        estadoReporte
                );

        this.tipoReporte =
                validarTipoReporte(
                        tipoReporte
                );

        this.descripcion =
                validarDescripcion(
                        descripcion
                );

        this.direccionReferencia =
                validarDireccionReferencia(
                        direccionReferencia
                );

        this.evidenciaUrl =
                validarEvidenciaUrl(
                        evidenciaUrl
                );

        this.observacionRespuesta =
                UtilTexto.obtenerValorOpcional(
                        observacionRespuesta
                );

        this.fechaReporte = fechaReporte;
    }

    public static ReporteDominio crearNuevo(
            CiudadanoDominio ciudadano,
            ZonaDominio zona,
            EstadoReporteDominio estadoReporte,
            TipoReporteDominio tipoReporte,
            String descripcion,
            String direccionReferencia,
            String evidenciaUrl) {

        return new ReporteDominio(
                null,
                ciudadano,
                zona,
                estadoReporte,
                tipoReporte,
                descripcion,
                direccionReferencia,
                evidenciaUrl,
                null,
                null
        );
    }

    public static ReporteDominio reconstruir(
            Long idReporte,
            CiudadanoDominio ciudadano,
            ZonaDominio zona,
            EstadoReporteDominio estadoReporte,
            TipoReporteDominio tipoReporte,
            String descripcion,
            String direccionReferencia,
            String evidenciaUrl,
            String observacionRespuesta,
            OffsetDateTime fechaReporte) {

        return new ReporteDominio(
                validarIdReporte(idReporte),
                ciudadano,
                zona,
                estadoReporte,
                tipoReporte,
                descripcion,
                direccionReferencia,
                evidenciaUrl,
                observacionRespuesta,
                UtilObjeto.obtenerValorObligatorio(
                        fechaReporte,
                        "La fecha del reporte"
                )
        );
    }

    private static Long validarIdReporte(
            Long idReporte) {

        Long valorValidado =
                UtilObjeto.obtenerValorObligatorio(
                        idReporte,
                        "El identificador del reporte"
                );

        if (valorValidado <= 0) {
            throw new IllegalArgumentException(
                    "El identificador del reporte "
                    + "debe ser mayor que cero"
            );
        }

        return valorValidado;
    }

    private void validarConsistenciaPersistencia(
            Long idReporte,
            OffsetDateTime fechaReporte) {

        boolean idNulo = idReporte == null;
        boolean fechaNula = fechaReporte == null;

        if (idNulo != fechaNula) {
            throw new IllegalArgumentException(
                    "El identificador y la fecha del reporte "
                    + "deben existir al mismo tiempo"
            );
        }
    }

    private CiudadanoDominio validarCiudadano(
            CiudadanoDominio ciudadano) {

        return UtilObjeto.obtenerValorObligatorio(
                ciudadano,
                "El ciudadano"
        );
    }

    private ZonaDominio validarZona(
            ZonaDominio zona) {

        ZonaDominio valorValidado =
                UtilObjeto.obtenerValorObligatorio(
                        zona,
                        "La zona"
                );

        if (!valorValidado.isActivo()) {
            throw new IllegalArgumentException(
                    "La zona seleccionada debe estar activa"
            );
        }

        return valorValidado;
    }

    private EstadoReporteDominio validarEstadoReporte(
            EstadoReporteDominio estadoReporte) {

        EstadoReporteDominio valorValidado =
                UtilObjeto.obtenerValorObligatorio(
                        estadoReporte,
                        "El estado del reporte"
                );

        if (!valorValidado.isActivo()) {
            throw new IllegalArgumentException(
                    "El estado del reporte debe estar activo"
            );
        }

        return valorValidado;
    }

    private TipoReporteDominio validarTipoReporte(
            TipoReporteDominio tipoReporte) {

        TipoReporteDominio valorValidado =
                UtilObjeto.obtenerValorObligatorio(
                        tipoReporte,
                        "El tipo de reporte"
                );

        if (!valorValidado.isActivo()) {
            throw new IllegalArgumentException(
                    "El tipo de reporte seleccionado "
                    + "debe estar activo"
            );
        }

        return valorValidado;
    }

    private String validarDescripcion(
            String descripcion) {

        return UtilTexto
                .obtenerValorObligatorioConLongitud(
                        descripcion,
                        "La descripción del reporte",
                        LONGITUD_MINIMA_DESCRIPCION,
                        LONGITUD_MAXIMA_DESCRIPCION
                );
    }

    private String validarDireccionReferencia(
            String direccionReferencia) {

        return UtilTexto
                .obtenerValorOpcionalConLongitud(
                        direccionReferencia,
                        "La dirección de referencia",
                        LONGITUD_MAXIMA_DIRECCION
                );
    }

    private String validarEvidenciaUrl(
            String evidenciaUrl) {

        return UtilTexto
                .obtenerValorOpcionalConLongitud(
                        evidenciaUrl,
                        "La dirección de la evidencia",
                        LONGITUD_MAXIMA_EVIDENCIA
                );
    }

    public boolean estaPersistido() {
        return idReporte != null
                && fechaReporte != null;
    }

    public Long getIdReporte() {
        return idReporte;
    }

    public CiudadanoDominio getCiudadano() {
        return ciudadano;
    }

    public ZonaDominio getZona() {
        return zona;
    }

    public EstadoReporteDominio getEstadoReporte() {
        return estadoReporte;
    }

    public TipoReporteDominio getTipoReporte() {
        return tipoReporte;
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
