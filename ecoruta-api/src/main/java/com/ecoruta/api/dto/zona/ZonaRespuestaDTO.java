package com.ecoruta.api.dto.zona;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public final class ZonaRespuestaDTO {

    private final Long idZona;

    private final String nombreZona;

    private final String tipoZona;

    private final String sector;

    private final String municipio;

    private final String horario;

    public ZonaRespuestaDTO(
            Long idZona,
            String nombreZona,
            String tipoZona,
            String sector,
            String municipio,
            String horario) {

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

        this.tipoZona =
                UtilTexto.obtenerValorObligatorio(
                    tipoZona,
                    "El tipo de zona"
                );

        this.sector =
                UtilTexto.obtenerValorObligatorio(
                    sector,
                    "El sector de la zona"
                );

        this.municipio =
                UtilTexto.obtenerValorObligatorio(
                    municipio,
                    "El municipio de la zona"
                );

        this.horario =
                UtilTexto.obtenerValorObligatorio(
                    horario,
                    "El horario de la zona"
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

    public String getHorario() {
        return horario;
    }
}
