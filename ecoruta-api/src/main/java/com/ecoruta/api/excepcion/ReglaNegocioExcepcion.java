package com.ecoruta.api.excepcion;

import com.ecoruta.api.utilitario.UtilTexto;

public final class ReglaNegocioExcepcion
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ReglaNegocioExcepcion(
            String mensaje) {

        super(validarMensaje(mensaje));
    }

    public ReglaNegocioExcepcion(
            String mensaje,
            Throwable causa) {

        super(
                validarMensaje(mensaje),
                causa
        );
    }

    private static String validarMensaje(
            String mensaje) {

        return UtilTexto.obtenerValorObligatorio(
                mensaje,
                "El mensaje de la excepción"
        );
    }
}