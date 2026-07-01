package com.ecoruta.api.excepcion;

import com.ecoruta.api.utilitario.UtilTexto;

public final class ConflictoDatosExcepcion
        extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ConflictoDatosExcepcion(
            String mensaje) {

        super(validarMensaje(mensaje));
    }

    public ConflictoDatosExcepcion(
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
