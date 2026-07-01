package com.ecoruta.api.negocio.ensamblador;

import org.springframework.stereotype.Component;

import com.ecoruta.api.dto.reporte.CrearReporteDTO;
import com.ecoruta.api.entidad.CiudadanoEntidad;
import com.ecoruta.api.negocio.dominio.CiudadanoDominio;
import com.ecoruta.api.utilitario.UtilObjeto;

@Component
public class CiudadanoEnsamblador {

    public CiudadanoDominio ensamblarDominio(
            CrearReporteDTO datos) {

        CrearReporteDTO datosValidados =
                UtilObjeto.obtenerValorObligatorio(
                        datos,
                        "Los datos del reporte"
                );

        return new CiudadanoDominio(
                datosValidados.getNombreCompleto(),
                datosValidados.getIdentificacion(),
                datosValidados.getContacto(),
                datosValidados.getCorreoElectronico()
        );
    }

    public CiudadanoDominio ensamblarDominio(
            CiudadanoEntidad entidad) {

        CiudadanoEntidad entidadValidada =
                UtilObjeto.obtenerValorObligatorio(
                        entidad,
                        "La entidad del ciudadano"
                );

        return new CiudadanoDominio(
                entidadValidada.getNombreCompleto(),
                entidadValidada.getIdentificacion(),
                entidadValidada.getContacto(),
                entidadValidada.getCorreoElectronico()
        );
    }

    public CiudadanoEntidad ensamblarEntidad(
            CiudadanoDominio dominio) {

        CiudadanoDominio dominioValidado =
                UtilObjeto.obtenerValorObligatorio(
                        dominio,
                        "El ciudadano de dominio"
                );

        return new CiudadanoEntidad(
                dominioValidado.getNombreCompleto(),
                dominioValidado.getIdentificacion(),
                dominioValidado.getContacto(),
                dominioValidado.getCorreoElectronico()
        );
    }

    public void actualizarEntidad(
            CiudadanoEntidad entidad,
            CiudadanoDominio dominio) {

        CiudadanoEntidad entidadValidada =
                UtilObjeto.obtenerValorObligatorio(
                        entidad,
                        "La entidad del ciudadano"
                );

        CiudadanoDominio dominioValidado =
                UtilObjeto.obtenerValorObligatorio(
                        dominio,
                        "El ciudadano de dominio"
                );

        validarMismaIdentificacion(
                entidadValidada,
                dominioValidado
        );

        entidadValidada.actualizarDatosPersonales(
                dominioValidado.getNombreCompleto(),
                dominioValidado.getContacto(),
                dominioValidado.getCorreoElectronico()
        );
    }

    private void validarMismaIdentificacion(
            CiudadanoEntidad entidad,
            CiudadanoDominio dominio) {

        if (!entidad.getIdentificacion()
                .equals(dominio.getIdentificacion())) {

            throw new IllegalArgumentException(
                    "No se puede cambiar la identificación "
                    + "del ciudadano"
            );
        }
    }
}