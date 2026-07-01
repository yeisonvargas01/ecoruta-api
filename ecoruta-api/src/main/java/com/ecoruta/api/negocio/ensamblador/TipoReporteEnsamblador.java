package com.ecoruta.api.negocio.ensamblador;

import org.springframework.stereotype.Component;

import com.ecoruta.api.dto.tipo.TipoReporteRespuestaDTO;
import com.ecoruta.api.entidad.TipoReporteEntidad;
import com.ecoruta.api.negocio.dominio.TipoReporteDominio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Component
public class TipoReporteEnsamblador {

    public TipoReporteDominio ensamblarDominio(
            TipoReporteEntidad entidad) {

        TipoReporteEntidad entidadValidada =
                UtilObjeto.obtenerValorObligatorio(
                        entidad,
                        "La entidad del tipo de reporte"
                );

        return new TipoReporteDominio(
                entidadValidada.getIdTipoReporte(),
                entidadValidada.getNombreTipo(),
                entidadValidada.getDescripcionTipo(),
                entidadValidada.isActivo()
        );
    }

    public TipoReporteRespuestaDTO ensamblarRespuesta(
            TipoReporteDominio dominio) {

        TipoReporteDominio dominioValidado =
                UtilObjeto.obtenerValorObligatorio(
                        dominio,
                        "El tipo de reporte de dominio"
                );

        validarTipoReporteActivo(
                dominioValidado
        );

        return new TipoReporteRespuestaDTO(
                dominioValidado.getIdTipoReporte(),
                dominioValidado.getNombreTipo(),
                dominioValidado.getDescripcionTipo()
        );
    }

    public TipoReporteRespuestaDTO ensamblarRespuesta(
            TipoReporteEntidad entidad) {

        TipoReporteDominio dominio =
                ensamblarDominio(entidad);

        return ensamblarRespuesta(dominio);
    }

    private void validarTipoReporteActivo(
            TipoReporteDominio dominio) {

        if (!dominio.isActivo()) {
            throw new IllegalArgumentException(
                    "No se puede generar una respuesta "
                    + "para un tipo de reporte inactivo"
            );
        }
    }
}
