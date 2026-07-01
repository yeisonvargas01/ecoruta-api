package com.ecoruta.api.negocio.casouso.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecoruta.api.dto.zona.ZonaRespuestaDTO;
import com.ecoruta.api.negocio.casouso.ConsultarZonasCasoUso;
import com.ecoruta.api.negocio.ensamblador.ZonaEnsamblador;
import com.ecoruta.api.repositorio.ZonaRepositorio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Service
public class ConsultarZonasCasoUsoImpl
        implements ConsultarZonasCasoUso {

    private final ZonaRepositorio zonaRepositorio;
    private final ZonaEnsamblador zonaEnsamblador;

    public ConsultarZonasCasoUsoImpl(
            ZonaRepositorio zonaRepositorio,
            ZonaEnsamblador zonaEnsamblador) {

        this.zonaRepositorio =
                UtilObjeto.obtenerValorObligatorio(
                        zonaRepositorio,
                        "El repositorio de zonas"
                );

        this.zonaEnsamblador =
                UtilObjeto.obtenerValorObligatorio(
                        zonaEnsamblador,
                        "El ensamblador de zonas"
                );
    }

    @Override
    @Transactional(readOnly = true)
    public List<ZonaRespuestaDTO> ejecutar() {

        return zonaRepositorio
                .findByActivoTrueOrderByNombreZonaAsc()
                .stream()
                .map(zonaEntidad ->
                        zonaEnsamblador.ensamblarRespuesta(
                                zonaEntidad
                        )
                )
                .toList();
    }
}