package com.ecoruta.api.utilitario;

import java.util.Locale;
import java.util.Objects;

public final class UtilTexto {

    private UtilTexto() {
        // Evita que esta clase utilitaria sea instanciada.
    }

    public static String obtenerValorObligatorio(
            String valor,
            String nombreCampo) {

        Objects.requireNonNull(
                valor,
                nombreCampo + " no puede ser nulo"
        );

        String valorNormalizado = valor.trim();

        if (valorNormalizado.isBlank()) {
            throw new IllegalArgumentException(
                    nombreCampo + " no puede estar vacío"
            );
        }

        return valorNormalizado;
    }

    public static String obtenerValorObligatorioEnMinuscula(
            String valor,
            String nombreCampo) {

        return obtenerValorObligatorio(valor, nombreCampo)
                .toLowerCase(Locale.ROOT);
    }
    
    public static String obtenerValorOpcional(String valor) {

        if (valor == null) {
            return null;
        }

        String valorNormalizado = valor.trim();

        if (valorNormalizado.isBlank()) {
            return null;
        }

        return valorNormalizado;
    }
    
}
