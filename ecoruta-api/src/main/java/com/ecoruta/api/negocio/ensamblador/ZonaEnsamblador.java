package com.ecoruta.api.negocio.ensamblador;

import org.springframework.stereotype.Component;

import com.ecoruta.api.dto.zona.ZonaRespuestaDTO;
import com.ecoruta.api.entidad.ZonaEntidad;
import com.ecoruta.api.negocio.dominio.ZonaDominio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Component
public class ZonaEnsamblador {

    public ZonaDominio ensamblarDominio(
            ZonaEntidad entidad) {

        ZonaEntidad entidadValidada =
                UtilObjeto.obtenerValorObligatorio(
                        entidad,
                        "La entidad de la zona"
                );

        return new ZonaDominio(
                entidadValidada.getIdZona(),
                entidadValidada.getNombreZona(),
                entidadValidada.getTipoZona(),
                entidadValidada.getSector(),
                entidadValidada.getMunicipio(),
                entidadValidada.isActivo(),
                entidadValidada.getHorario()
        );
    }

    public ZonaRespuestaDTO ensamblarRespuesta(
            ZonaDominio dominio) {

        ZonaDominio dominioValidado =
                UtilObjeto.obtenerValorObligatorio(
                        dominio,
                        "La zona de dominio"
                );

        validarZonaActiva(dominioValidado);

        return new ZonaRespuestaDTO(
                dominioValidado.getIdZona(),
                dominioValidado.getNombreZona(),
                dominioValidado.getTipoZona(),
                dominioValidado.getSector(),
                dominioValidado.getMunicipio(),
                dominioValidado.getHorario()
        );
    }

    public ZonaRespuestaDTO ensamblarRespuesta(
            ZonaEntidad entidad) {

        ZonaDominio dominio =
                ensamblarDominio(entidad);

        return ensamblarRespuesta(dominio);
    }

    private void validarZonaActiva(
            ZonaDominio dominio) {

        if (!dominio.isActivo()) {
            throw new IllegalArgumentException(
                    "No se puede generar una respuesta "
                    + "para una zona inactiva"
            );
        }
    }
}
