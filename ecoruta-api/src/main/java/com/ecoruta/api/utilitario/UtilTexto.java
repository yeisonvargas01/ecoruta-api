package com.ecoruta.api.utilitario;

import java.util.Locale;
import java.util.Objects;

public final class UtilTexto {

    private UtilTexto() {
        /*
         * Evita que esta clase utilitaria sea instanciada.
         */
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

        return obtenerValorObligatorio(
                valor,
                nombreCampo
        ).toLowerCase(Locale.ROOT);
    }

    public static String obtenerValorOpcional(
            String valor) {

        if (valor == null) {
            return null;
        }

        String valorNormalizado = valor.trim();

        if (valorNormalizado.isBlank()) {
            return null;
        }

        return valorNormalizado;
    }

    public static String obtenerValorObligatorioConLongitud(
            String valor,
            String nombreCampo,
            int longitudMinima,
            int longitudMaxima) {

        validarRangoLongitud(
                longitudMinima,
                longitudMaxima
        );

        String valorNormalizado =
                obtenerValorObligatorio(
                        valor,
                        nombreCampo
                );

        int longitud = valorNormalizado.length();

        if (longitud < longitudMinima
                || longitud > longitudMaxima) {

            throw new IllegalArgumentException(
                    nombreCampo
                    + " debe tener entre "
                    + longitudMinima
                    + " y "
                    + longitudMaxima
                    + " caracteres"
            );
        }

        return valorNormalizado;
    }

    public static String obtenerValorOpcionalConLongitud(
            String valor,
            String nombreCampo,
            int longitudMaxima) {

        if (longitudMaxima <= 0) {
            throw new IllegalArgumentException(
                    "La longitud máxima debe ser mayor que cero"
            );
        }

        String valorNormalizado =
                obtenerValorOpcional(valor);

        if (valorNormalizado != null
                && valorNormalizado.length() > longitudMaxima) {

            throw new IllegalArgumentException(
                    nombreCampo
                    + " no puede superar los "
                    + longitudMaxima
                    + " caracteres"
            );
        }

        return valorNormalizado;
    }

    private static void validarRangoLongitud(
            int longitudMinima,
            int longitudMaxima) {

        if (longitudMinima < 0
                || longitudMaxima < longitudMinima) {

            throw new IllegalArgumentException(
                    "El rango de longitud no es válido"
            );
        }
    }
}