package com.ecoruta.api.utilitario;

import java.util.Objects;

public final class UtilObjeto {

    private UtilObjeto() {
        
    }

    public static <T> T obtenerValorObligatorio(
            T valor,
            String nombreCampo) {

        return Objects.requireNonNull(
                valor,
                nombreCampo + " no puede ser nulo"
        );
    }
}
