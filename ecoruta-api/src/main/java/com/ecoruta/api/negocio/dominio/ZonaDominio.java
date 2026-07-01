package com.ecoruta.api.negocio.dominio;

import java.util.Locale;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public final class ZonaDominio {

    private static final int LONGITUD_MINIMA_NOMBRE = 3;
    private static final int LONGITUD_MAXIMA_NOMBRE = 120;

    private static final int LONGITUD_MINIMA_TIPO = 3;
    private static final int LONGITUD_MAXIMA_TIPO = 30;

    private static final int LONGITUD_MINIMA_SECTOR = 3;
    private static final int LONGITUD_MAXIMA_SECTOR = 30;

    private static final int LONGITUD_MINIMA_MUNICIPIO = 3;
    private static final int LONGITUD_MAXIMA_MUNICIPIO = 80;

    private static final int LONGITUD_MINIMA_HORARIO = 3;
    private static final int LONGITUD_MAXIMA_HORARIO = 80;

    private final Long idZona;

    private final String nombreZona;

    private final String tipoZona;

    private final String sector;

    private final String municipio;

    private final boolean activo;

    private final String horario;

    public ZonaDominio(
            Long idZona,
            String nombreZona,
            String tipoZona,
            String sector,
            String municipio,
            boolean activo,
            String horario) {

        this.idZona = validarIdZona(idZona);

        this.nombreZona =
                validarNombreZona(nombreZona);

        this.tipoZona =
                validarTipoZona(tipoZona);

        this.sector =
                validarSector(sector);

        this.municipio =
                validarMunicipio(municipio);

        this.activo = activo;

        this.horario =
                validarHorario(horario);
    }

    private Long validarIdZona(
            Long idZona) {

        Long valorValidado =
                UtilObjeto.obtenerValorObligatorio(
                        idZona,
                        "El identificador de la zona"
                );

        if (valorValidado <= 0) {
            throw new IllegalArgumentException(
                    "El identificador de la zona "
                    + "debe ser mayor que cero"
            );
        }

        return valorValidado;
    }

    private String validarNombreZona(
            String nombreZona) {

        return UtilTexto.obtenerValorObligatorioConLongitud(
                nombreZona,
                "El nombre de la zona",
                LONGITUD_MINIMA_NOMBRE,
                LONGITUD_MAXIMA_NOMBRE
        );
    }

    private String validarTipoZona(
            String tipoZona) {

        return UtilTexto
                .obtenerValorObligatorioConLongitud(
                        tipoZona,
                        "El tipo de zona",
                        LONGITUD_MINIMA_TIPO,
                        LONGITUD_MAXIMA_TIPO
                )
                .toLowerCase(Locale.ROOT);
    }

    private String validarSector(
            String sector) {

        return UtilTexto
                .obtenerValorObligatorioConLongitud(
                        sector,
                        "El sector de la zona",
                        LONGITUD_MINIMA_SECTOR,
                        LONGITUD_MAXIMA_SECTOR
                )
                .toLowerCase(Locale.ROOT);
    }

    private String validarMunicipio(
            String municipio) {

        return UtilTexto.obtenerValorObligatorioConLongitud(
                municipio,
                "El municipio de la zona",
                LONGITUD_MINIMA_MUNICIPIO,
                LONGITUD_MAXIMA_MUNICIPIO
        );
    }

    private String validarHorario(
            String horario) {

        return UtilTexto.obtenerValorObligatorioConLongitud(
                horario,
                "El horario de la zona",
                LONGITUD_MINIMA_HORARIO,
                LONGITUD_MAXIMA_HORARIO
        );
    }

    public Long getIdZona() {
        return idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public String getSector() {
        return sector;
    }

    public String getMunicipio() {
        return municipio;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getHorario() {
        return horario;
    }
}