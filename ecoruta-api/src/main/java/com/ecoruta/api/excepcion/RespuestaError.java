package com.ecoruta.api.excepcion;

import java.time.OffsetDateTime;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

public record RespuestaError(
        int estado,
        String error,
        String mensaje,
        String ruta,
        OffsetDateTime fecha) {

    private static final int ESTADO_HTTP_MINIMO = 400;
    private static final int ESTADO_HTTP_MAXIMO = 599;

    public RespuestaError {

        if (estado < ESTADO_HTTP_MINIMO
                || estado > ESTADO_HTTP_MAXIMO) {

            throw new IllegalArgumentException(
                    "El estado HTTP debe estar entre "
                    + ESTADO_HTTP_MINIMO
                    + " y "
                    + ESTADO_HTTP_MAXIMO
            );
        }

        error = UtilTexto.obtenerValorObligatorio(
                error,
                "El nombre del error"
        );

        mensaje = UtilTexto.obtenerValorObligatorio(
                mensaje,
                "El mensaje del error"
        );

        ruta = UtilTexto.obtenerValorObligatorio(
                ruta,
                "La ruta de la solicitud"
        );

        fecha = UtilObjeto.obtenerValorObligatorio(
                fecha,
                "La fecha del error"
        );
    }
}
