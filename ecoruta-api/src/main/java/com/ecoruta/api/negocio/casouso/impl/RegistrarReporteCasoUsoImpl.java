package com.ecoruta.api.negocio.casouso.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecoruta.api.dto.reporte.CrearReporteDTO;
import com.ecoruta.api.dto.reporte.ReporteRespuestaDTO;
import com.ecoruta.api.entidad.CiudadanoEntidad;
import com.ecoruta.api.entidad.EstadoReporteEntidad;
import com.ecoruta.api.entidad.ReporteEntidad;
import com.ecoruta.api.entidad.TipoReporteEntidad;
import com.ecoruta.api.entidad.ZonaEntidad;
import com.ecoruta.api.excepcion.ConflictoDatosExcepcion;
import com.ecoruta.api.excepcion.RecursoNoEncontradoExcepcion;
import com.ecoruta.api.negocio.casouso.RegistrarReporteCasoUso;
import com.ecoruta.api.negocio.dominio.CiudadanoDominio;
import com.ecoruta.api.negocio.dominio.EstadoReporteDominio;
import com.ecoruta.api.negocio.dominio.ReporteDominio;
import com.ecoruta.api.negocio.dominio.TipoReporteDominio;
import com.ecoruta.api.negocio.dominio.ZonaDominio;
import com.ecoruta.api.negocio.ensamblador.CiudadanoEnsamblador;
import com.ecoruta.api.negocio.ensamblador.EstadoReporteEnsamblador;
import com.ecoruta.api.negocio.ensamblador.ReporteEnsamblador;
import com.ecoruta.api.negocio.ensamblador.TipoReporteEnsamblador;
import com.ecoruta.api.negocio.ensamblador.ZonaEnsamblador;
import com.ecoruta.api.repositorio.CiudadanoRepositorio;
import com.ecoruta.api.repositorio.EstadoReporteRepositorio;
import com.ecoruta.api.repositorio.ReporteRepositorio;
import com.ecoruta.api.repositorio.TipoReporteRepositorio;
import com.ecoruta.api.repositorio.ZonaRepositorio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Service
public class RegistrarReporteCasoUsoImpl
        implements RegistrarReporteCasoUso {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    RegistrarReporteCasoUsoImpl.class
            );

    private static final String ESTADO_INICIAL =
            "Pendiente";

    private final CiudadanoRepositorio ciudadanoRepositorio;
    private final ZonaRepositorio zonaRepositorio;
    private final EstadoReporteRepositorio estadoReporteRepositorio;
    private final TipoReporteRepositorio tipoReporteRepositorio;
    private final ReporteRepositorio reporteRepositorio;

    private final CiudadanoEnsamblador ciudadanoEnsamblador;
    private final ZonaEnsamblador zonaEnsamblador;
    private final EstadoReporteEnsamblador estadoReporteEnsamblador;
    private final TipoReporteEnsamblador tipoReporteEnsamblador;
    private final ReporteEnsamblador reporteEnsamblador;

    public RegistrarReporteCasoUsoImpl(
            CiudadanoRepositorio ciudadanoRepositorio,
            ZonaRepositorio zonaRepositorio,
            EstadoReporteRepositorio estadoReporteRepositorio,
            TipoReporteRepositorio tipoReporteRepositorio,
            ReporteRepositorio reporteRepositorio,
            CiudadanoEnsamblador ciudadanoEnsamblador,
            ZonaEnsamblador zonaEnsamblador,
            EstadoReporteEnsamblador estadoReporteEnsamblador,
            TipoReporteEnsamblador tipoReporteEnsamblador,
            ReporteEnsamblador reporteEnsamblador) {

        this.ciudadanoRepositorio =
                UtilObjeto.obtenerValorObligatorio(
                        ciudadanoRepositorio,
                        "El repositorio del ciudadano"
                );

        this.zonaRepositorio =
                UtilObjeto.obtenerValorObligatorio(
                        zonaRepositorio,
                        "El repositorio de la zona"
                );

        this.estadoReporteRepositorio =
                UtilObjeto.obtenerValorObligatorio(
                        estadoReporteRepositorio,
                        "El repositorio del estado del reporte"
                );

        this.tipoReporteRepositorio =
                UtilObjeto.obtenerValorObligatorio(
                        tipoReporteRepositorio,
                        "El repositorio del tipo de reporte"
                );

        this.reporteRepositorio =
                UtilObjeto.obtenerValorObligatorio(
                        reporteRepositorio,
                        "El repositorio del reporte"
                );

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

        this.reporteEnsamblador =
                UtilObjeto.obtenerValorObligatorio(
                        reporteEnsamblador,
                        "El ensamblador del reporte"
                );
    }

    @Override
    @Transactional
    public ReporteRespuestaDTO ejecutar(
            CrearReporteDTO datos) {

        CrearReporteDTO datosValidados =
                UtilObjeto.obtenerValorObligatorio(
                        datos,
                        "Los datos para crear el reporte"
                );

        CiudadanoDominio ciudadanoDominio =
                ciudadanoEnsamblador.ensamblarDominio(
                        datosValidados
                );

        validarCorreoDisponible(
                ciudadanoDominio
        );

        CiudadanoEntidad ciudadanoEntidad =
                obtenerOCrearCiudadano(
                        ciudadanoDominio
                );

        ZonaEntidad zonaEntidad =
                obtenerZonaActiva(
                        datosValidados.getIdZona()
                );

        TipoReporteEntidad tipoReporteEntidad =
                obtenerTipoReporteActivo(
                        datosValidados.getIdTipoReporte()
                );

        EstadoReporteEntidad estadoEntidad =
                obtenerEstadoInicial();

        ZonaDominio zonaDominio =
                zonaEnsamblador.ensamblarDominio(
                        zonaEntidad
                );

        TipoReporteDominio tipoReporteDominio =
                tipoReporteEnsamblador.ensamblarDominio(
                        tipoReporteEntidad
                );

        EstadoReporteDominio estadoDominio =
                estadoReporteEnsamblador.ensamblarDominio(
                        estadoEntidad
                );

        ReporteDominio reporteDominio =
                ReporteDominio.crearNuevo(
                        ciudadanoDominio,
                        zonaDominio,
                        estadoDominio,
                        tipoReporteDominio,
                        datosValidados.getDescripcion(),
                        datosValidados.getDireccionReferencia(),
                        datosValidados.getEvidenciaUrl()
                );

        ReporteEntidad reporteEntidad =
                reporteEnsamblador.ensamblarEntidad(
                        reporteDominio,
                        ciudadanoEntidad,
                        zonaEntidad,
                        estadoEntidad,
                        tipoReporteEntidad
                );

        ReporteEntidad reporteGuardado =
                reporteRepositorio.saveAndFlush(
                        reporteEntidad
                );

        LOGGER.info(
                "Reporte registrado correctamente con id {}",
                reporteGuardado.getIdReporte()
        );

        return reporteEnsamblador.ensamblarRespuesta(
                reporteGuardado
        );
    }

    private void validarCorreoDisponible(
            CiudadanoDominio ciudadanoDominio) {

        boolean correoAsociadoAOtroCiudadano =
                ciudadanoRepositorio
                    .findByCorreoElectronicoIgnoreCase(
                            ciudadanoDominio
                                .getCorreoElectronico()
                    )
                    .filter(ciudadanoEncontrado ->
                            !ciudadanoEncontrado
                                .getIdentificacion()
                                .equals(
                                    ciudadanoDominio
                                        .getIdentificacion()
                                )
                    )
                    .isPresent();

        if (correoAsociadoAOtroCiudadano) {
            throw new ConflictoDatosExcepcion(
                    "El correo electrónico ya está "
                    + "asociado a otro ciudadano"
            );
        }
    }

    private CiudadanoEntidad obtenerOCrearCiudadano(
            CiudadanoDominio ciudadanoDominio) {

        return ciudadanoRepositorio
                .findByIdentificacion(
                        ciudadanoDominio
                                .getIdentificacion()
                )
                .map(ciudadanoExistente ->
                        actualizarCiudadanoExistente(
                                ciudadanoExistente,
                                ciudadanoDominio
                        )
                )
                .orElseGet(() ->
                        crearCiudadano(
                                ciudadanoDominio
                        )
                );
    }

    private CiudadanoEntidad actualizarCiudadanoExistente(
            CiudadanoEntidad ciudadanoEntidad,
            CiudadanoDominio ciudadanoDominio) {

        ciudadanoEnsamblador.actualizarEntidad(
                ciudadanoEntidad,
                ciudadanoDominio
        );

        return ciudadanoRepositorio.save(
                ciudadanoEntidad
        );
    }

    private CiudadanoEntidad crearCiudadano(
            CiudadanoDominio ciudadanoDominio) {

        CiudadanoEntidad nuevaEntidad =
                ciudadanoEnsamblador.ensamblarEntidad(
                        ciudadanoDominio
                );

        return ciudadanoRepositorio.save(
                nuevaEntidad
        );
    }

    private ZonaEntidad obtenerZonaActiva(
            Long idZona) {

        return zonaRepositorio
                .findByIdZonaAndActivoTrue(
                        idZona
                )
                .orElseThrow(() ->
                    new RecursoNoEncontradoExcepcion(
                            "La zona seleccionada no existe "
                            + "o se encuentra inactiva"
                    )
                );
    }

    private TipoReporteEntidad obtenerTipoReporteActivo(
            Long idTipoReporte) {

        return tipoReporteRepositorio
                .findByIdTipoReporteAndActivoTrue(
                        idTipoReporte
                )
                .orElseThrow(() ->
                    new RecursoNoEncontradoExcepcion(
                            "El tipo de reporte seleccionado "
                            + "no existe o se encuentra inactivo"
                    )
                );
    }

    private EstadoReporteEntidad obtenerEstadoInicial() {

        return estadoReporteRepositorio
                .findByNombreEstadoIgnoreCaseAndActivoTrue(
                        ESTADO_INICIAL
                )
                .orElseThrow(() ->
                    new RecursoNoEncontradoExcepcion(
                            "El estado inicial Pendiente "
                            + "no se encuentra configurado"
                    )
                );
    }
}
