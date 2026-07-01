package com.ecoruta.api.negocio.dominio;

import java.util.Locale;
import java.util.regex.Pattern;

import com.ecoruta.api.utilitario.UtilTexto;

public final class CiudadanoDominio {

    private static final int LONGITUD_MINIMA_NOMBRE = 3;
    private static final int LONGITUD_MAXIMA_NOMBRE = 120;

    private static final int LONGITUD_MINIMA_IDENTIFICACION = 5;
    private static final int LONGITUD_MAXIMA_IDENTIFICACION = 30;

    private static final int LONGITUD_MINIMA_CONTACTO = 7;
    private static final int LONGITUD_MAXIMA_CONTACTO = 30;

    private static final int LONGITUD_MAXIMA_CORREO = 120;

    private static final Pattern PATRON_NOMBRE =
            Pattern.compile("^[\\p{L} .'-]+$");

    private static final Pattern PATRON_IDENTIFICACION =
            Pattern.compile("^[A-Za-z0-9.-]+$");

    private static final Pattern PATRON_CONTACTO =
            Pattern.compile("^[0-9+()\\s-]+$");

    private static final Pattern PATRON_CORREO =
            Pattern.compile(
                "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"
            );

    private String nombreCompleto;

    private final String identificacion;

    private String contacto;

    private String correoElectronico;

    public CiudadanoDominio(
            String nombreCompleto,
            String identificacion,
            String contacto,
            String correoElectronico) {

        this.identificacion =
                validarIdentificacion(identificacion);

        actualizarDatosPersonales(
                nombreCompleto,
                contacto,
                correoElectronico
        );
    }

    public void actualizarDatosPersonales(
            String nombreCompleto,
            String contacto,
            String correoElectronico) {

        this.nombreCompleto =
                validarNombreCompleto(
                        nombreCompleto
                );

        this.contacto =
                validarContacto(
                        contacto
                );

        this.correoElectronico =
                validarCorreoElectronico(
                        correoElectronico
                );
    }

    private String validarNombreCompleto(
            String nombreCompleto) {

        String valorNormalizado =
                UtilTexto.obtenerValorObligatorioConLongitud(
                        nombreCompleto,
                        "El nombre completo",
                        LONGITUD_MINIMA_NOMBRE,
                        LONGITUD_MAXIMA_NOMBRE
                );

        if (!PATRON_NOMBRE
                .matcher(valorNormalizado)
                .matches()) {

            throw new IllegalArgumentException(
                    "El nombre completo contiene "
                    + "caracteres no permitidos"
            );
        }

        return valorNormalizado;
    }

    private String validarIdentificacion(
            String identificacion) {

        String valorNormalizado =
                UtilTexto.obtenerValorObligatorioConLongitud(
                        identificacion,
                        "La identificación",
                        LONGITUD_MINIMA_IDENTIFICACION,
                        LONGITUD_MAXIMA_IDENTIFICACION
                );

        if (!PATRON_IDENTIFICACION
                .matcher(valorNormalizado)
                .matches()) {

            throw new IllegalArgumentException(
                    "La identificación solo puede contener "
                    + "letras, números, puntos y guiones"
            );
        }

        return valorNormalizado;
    }

    private String validarContacto(
            String contacto) {

        String valorNormalizado =
                UtilTexto.obtenerValorObligatorioConLongitud(
                        contacto,
                        "El número de contacto",
                        LONGITUD_MINIMA_CONTACTO,
                        LONGITUD_MAXIMA_CONTACTO
                );

        if (!PATRON_CONTACTO
                .matcher(valorNormalizado)
                .matches()) {

            throw new IllegalArgumentException(
                    "El número de contacto contiene "
                    + "caracteres no permitidos"
            );
        }

        return valorNormalizado;
    }

    private String validarCorreoElectronico(
            String correoElectronico) {

        String valorNormalizado =
                UtilTexto.obtenerValorObligatorioConLongitud(
                        correoElectronico,
                        "El correo electrónico",
                        3,
                        LONGITUD_MAXIMA_CORREO
                )
                .toLowerCase(Locale.ROOT);

        if (!PATRON_CORREO
                .matcher(valorNormalizado)
                .matches()) {

            throw new IllegalArgumentException(
                    "El correo electrónico no tiene "
                    + "un formato válido"
            );
        }

        return valorNormalizado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getContacto() {
        return contacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
}