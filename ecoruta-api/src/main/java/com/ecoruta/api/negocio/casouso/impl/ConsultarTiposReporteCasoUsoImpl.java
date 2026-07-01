package com.ecoruta.api.negocio.casouso.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecoruta.api.dto.tipo.TipoReporteRespuestaDTO;
import com.ecoruta.api.negocio.casouso.ConsultarTiposReporteCasoUso;
import com.ecoruta.api.negocio.ensamblador.TipoReporteEnsamblador;
import com.ecoruta.api.repositorio.TipoReporteRepositorio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Service
public class ConsultarTiposReporteCasoUsoImpl
        implements ConsultarTiposReporteCasoUso {

    private final TipoReporteRepositorio tipoReporteRepositorio;

    private final TipoReporteEnsamblador tipoReporteEnsamblador;

    public ConsultarTiposReporteCasoUsoImpl(
            TipoReporteRepositorio tipoReporteRepositorio,
            TipoReporteEnsamblador tipoReporteEnsamblador) {

        this.tipoReporteRepositorio =
                UtilObjeto.obtenerValorObligatorio(
                        tipoReporteRepositorio,
                        "El repositorio de tipos de reporte"
                );

        this.tipoReporteEnsamblador =
                UtilObjeto.obtenerValorObligatorio(
                        tipoReporteEnsamblador,
                        "El ensamblador de tipos de reporte"
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoReporteRespuestaDTO> ejecutar() {

        return tipoReporteRepositorio
                .findByActivoTrueOrderByNombreTipoAsc()
                .stream()
                .map(tipoReporteEntidad ->
                        tipoReporteEnsamblador
                                .ensamblarRespuesta(
                                        tipoReporteEntidad
                                )
                )
                .toList();
    }
}
