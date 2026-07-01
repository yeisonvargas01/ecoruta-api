package com.ecoruta.api.negocio.ensamblador;

import org.springframework.stereotype.Component;

import com.ecoruta.api.dto.reporte.ReporteRespuestaDTO;
import com.ecoruta.api.entidad.CiudadanoEntidad;
import com.ecoruta.api.entidad.EstadoReporteEntidad;
import com.ecoruta.api.entidad.ReporteEntidad;
import com.ecoruta.api.entidad.TipoReporteEntidad;
import com.ecoruta.api.entidad.ZonaEntidad;
import com.ecoruta.api.negocio.dominio.ReporteDominio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Component
public class ReporteEnsamblador {

    private final CiudadanoEnsamblador ciudadanoEnsamblador;
    private final ZonaEnsamblador zonaEnsamblador;
    private final EstadoReporteEnsamblador estadoReporteEnsamblador;
    private final TipoReporteEnsamblador tipoReporteEnsamblador;

    public ReporteEnsamblador(
            CiudadanoEnsamblador ciudadanoEnsamblador,
            ZonaEnsamblador zonaEnsamblador,
            EstadoReporteEnsamblador estadoReporteEnsamblador,
            TipoReporteEnsamblador tipoReporteEnsamblador) {

        this.ciudadanoEnsamblador =
                UtilObjeto.obtenerValorObligatorio(
                        ciudadanoEnsamblador,
                        "El ensamblador del ciudadano"
                );

        this.zonaEnsamblador =
                UtilObjeto.obtenerValorObligatorio(
                        zonaEnsamblador,
                        "El ensamblador de la zona"
                );

        this.estadoReporteEnsamblador =
                UtilObjeto.obtenerValorObligatorio(
                        estadoReporteEnsamblador,
                        "El ensamblador del estado del reporte"
                );

        this.tipoReporteEnsamblador =
                UtilObjeto.obtenerValorObligatorio(
                        tipoReporteEnsamblador,
                        "El ensamblador del tipo de reporte"
                );
    }

    public ReporteEntidad ensamblarEntidad(
            ReporteDominio dominio,
            CiudadanoEntidad ciudadanoEntidad,
            ZonaEntidad zonaEntidad,
            EstadoReporteEntidad estadoEntidad,
            TipoReporteEntidad tipoReporteEntidad) {

        ReporteDominio dominioValidado =
                UtilObjeto.obtenerValorObligatorio(
                        dominio,
                        "El reporte de dominio"
                );

        CiudadanoEntidad ciudadanoValidado =
                UtilObjeto.obtenerValorObligatorio(
                        ciudadanoEntidad,
                        "La entidad del ciudadano"
                );

        ZonaEntidad zonaValidada =
                UtilObjeto.obtenerValorObligatorio(
                        zonaEntidad,
                        "La entidad de la zona"
                );

        EstadoReporteEntidad estadoValidado =
                UtilObjeto.obtenerValorObligatorio(
                        estadoEntidad,
                        "La entidad del estado del reporte"
                );

        TipoReporteEntidad tipoValidado =
                UtilObjeto.obtenerValorObligatorio(
                        tipoReporteEntidad,
                        "La entidad del tipo de reporte"
                );

        validarReporteNuevo(dominioValidado);

        validarCorrespondenciaCiudadano(
                dominioValidado,
                ciudadanoValidado
        );

        validarCorrespondenciaZona(
                dominioValidado,
                zonaValidada
        );

        validarCorrespondenciaEstado(
                dominioValidado,
                estadoValidado
        );

        validarCorrespondenciaTipoReporte(
                dominioValidado,
                tipoValidado
        );

        return new ReporteEntidad(
                ciudadanoValidado,
                zonaValidada,
                estadoValidado,
                tipoValidado,
                dominioValidado.getDescripcion(),
                dominioValidado.getDireccionReferencia(),
                dominioValidado.getEvidenciaUrl()
        );
    }

    public ReporteDominio ensamblarDominio(
            ReporteEntidad entidad) {

        ReporteEntidad entidadValidada =
                UtilObjeto.obtenerValorObligatorio(
                        entidad,
                        "La entidad del reporte"
                );

        return ReporteDominio.reconstruir(
                entidadValidada.getIdReporte(),

                ciudadanoEnsamblador.ensamblarDominio(
                        entidadValidada.getCiudadano()
                ),

                zonaEnsamblador.ensamblarDominio(
                        entidadValidada.getZona()
                ),

                estadoReporteEnsamblador.ensamblarDominio(
                        entidadValidada.getEstadoReporte()
                ),

                tipoReporteEnsamblador.ensamblarDominio(
                        entidadValidada.getTipoReporte()
                ),

                entidadValidada.getDescripcion(),
                entidadValidada.getDireccionReferencia(),
                entidadValidada.getEvidenciaUrl(),
                entidadValidada.getObservacionRespuesta(),
                entidadValidada.getFechaReporte()
        );
    }

    public ReporteRespuestaDTO ensamblarRespuesta(
            ReporteEntidad entidad) {

        ReporteDominio dominio =
                ensamblarDominio(entidad);

        return ensamblarRespuesta(dominio);
    }

    public ReporteRespuestaDTO ensamblarRespuesta(
            ReporteDominio dominio) {

        ReporteDominio dominioValidado =
                UtilObjeto.obtenerValorObligatorio(
                        dominio,
                        "El reporte de dominio"
                );

        validarReportePersistido(dominioValidado);

        return new ReporteRespuestaDTO(
                dominioValidado.getIdReporte(),
                dominioValidado
                        .getCiudadano()
                        .getNombreCompleto(),
                dominioValidado
                        .getZona()
                        .getIdZona(),
                dominioValidado
                        .getZona()
                        .getNombreZona(),
                dominioValidado
                        .getTipoReporte()
                        .getIdTipoReporte(),
                dominioValidado
                        .getTipoReporte()
                        .getNombreTipo(),
                dominioValidado
                        .getEstadoReporte()
                        .getIdEstado(),
                dominioValidado
                        .getEstadoReporte()
                        .getNombreEstado(),
                dominioValidado.getDescripcion(),
                dominioValidado.getDireccionReferencia(),
                dominioValidado.getEvidenciaUrl(),
                dominioValidado.getObservacionRespuesta(),
                dominioValidado.getFechaReporte()
        );
    }

    private void validarReporteNuevo(
            ReporteDominio dominio) {

        if (dominio.estaPersistido()) {
            throw new IllegalArgumentException(
                    "No se puede crear una entidad nueva "
                    + "a partir de un reporte ya persistido"
            );
        }
    }

    private void validarReportePersistido(
            ReporteDominio dominio) {

        if (!dominio.estaPersistido()) {
            throw new IllegalArgumentException(
                    "No se puede generar la respuesta "
                    + "de un reporte que todavía no ha sido guardado"
            );
        }
    }

    private void validarCorrespondenciaCiudadano(
            ReporteDominio dominio,
            CiudadanoEntidad entidad) {

        String identificacionDominio =
                dominio.getCiudadano()
                        .getIdentificacion();

        String identificacionEntidad =
                entidad.getIdentificacion();

        if (!identificacionDominio.equals(
                identificacionEntidad)) {

            throw new IllegalArgumentException(
                    "La entidad del ciudadano no corresponde "
                    + "con el ciudadano del reporte"
            );
        }
    }

    private void validarCorrespondenciaZona(
            ReporteDominio dominio,
            ZonaEntidad entidad) {

        Long idDominio =
                dominio.getZona()
                        .getIdZona();

        Long idEntidad =
                UtilObjeto.obtenerValorObligatorio(
                        entidad.getIdZona(),
                        "El identificador de la entidad de la zona"
                );

        if (!idDominio.equals(idEntidad)) {
            throw new IllegalArgumentException(
                    "La entidad de la zona no corresponde "
                    + "con la zona del reporte"
            );
        }
    }

    private void validarCorrespondenciaEstado(
            ReporteDominio dominio,
            EstadoReporteEntidad entidad) {

        Long idDominio =
                dominio.getEstadoReporte()
                        .getIdEstado();

        Long idEntidad =
                UtilObjeto.obtenerValorObligatorio(
                        entidad.getIdEstado(),
                        "El identificador de la entidad del estado"
                );

        if (!idDominio.equals(idEntidad)) {
            throw new IllegalArgumentException(
                    "La entidad del estado no corresponde "
                    + "con el estado del reporte"
            );
        }
    }

    private void validarCorrespondenciaTipoReporte(
            ReporteDominio dominio,
            TipoReporteEntidad entidad) {

        Long idDominio =
                dominio.getTipoReporte()
                        .getIdTipoReporte();

        Long idEntidad =
                UtilObjeto.obtenerValorObligatorio(
                        entidad.getIdTipoReporte(),
                        "El identificador de la entidad "
                        + "del tipo de reporte"
                );

        if (!idDominio.equals(idEntidad)) {
            throw new IllegalArgumentException(
                    "La entidad del tipo de reporte "
                    + "no corresponde con el tipo seleccionado"
            );
        }
    }
}
