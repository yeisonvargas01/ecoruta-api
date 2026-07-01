package com.ecoruta.api.negocio.ensamblador;

import org.springframework.stereotype.Component;

import com.ecoruta.api.entidad.EstadoReporteEntidad;
import com.ecoruta.api.negocio.dominio.EstadoReporteDominio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Component
public class EstadoReporteEnsamblador {

    public EstadoReporteDominio ensamblarDominio(
            EstadoReporteEntidad entidad) {

        EstadoReporteEntidad entidadValidada =
                UtilObjeto.obtenerValorObligatorio(
                        entidad,
                        "La entidad del estado del reporte"
                );

        return new EstadoReporteDominio(
                entidadValidada.getIdEstado(),
                entidadValidada.getNombreEstado(),
                entidadValidada.getDescripcionEstado(),
                entidadValidada.isActivo()
        );
    }
}