package com.ecoruta.api.entidad;

import java.time.OffsetDateTime;
import java.util.Locale;

import com.ecoruta.api.utilitario.UtilTexto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "ciudadano",
    schema = "ecoruta"
)
public class CiudadanoEntidad {

    private static final int LONGITUD_MINIMA_NOMBRE = 3;
    private static final int LONGITUD_MAXIMA_NOMBRE = 120;

    private static final int LONGITUD_MINIMA_IDENTIFICACION = 5;
    private static final int LONGITUD_MAXIMA_IDENTIFICACION = 30;

    private static final int LONGITUD_MINIMA_CONTACTO = 7;
    private static final int LONGITUD_MAXIMA_CONTACTO = 30;

    private static final int LONGITUD_MINIMA_CORREO = 3;
    private static final int LONGITUD_MAXIMA_CORREO = 120;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudadano")
    private Long idCiudadano;

    @Column(
        name = "nombre_completo",
        nullable = false,
        length = 120
    )
    private String nombreCompleto;

    @Column(
        name = "identificacion",
        nullable = false,
        unique = true,
        length = 30
    )
    private String identificacion;

    @Column(
        name = "contacto",
        nullable = false,
        length = 30
    )
    private String contacto;

    @Column(
        name = "correo_electronico",
        nullable = false,
        length = 120
    )
    private String correoElectronico;

    @Column(
        name = "fecha_registro",
        nullable = false,
        insertable = false,
        updatable = false
    )
    private OffsetDateTime fechaRegistro;

    protected CiudadanoEntidad() {
        
    }

    public CiudadanoEntidad(
            String nombreCompleto,
            String identificacion,
            String contacto,
            String correoElectronico) {

        this.identificacion =
                validarIdentificacion(
                        identificacion
                );

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
                UtilTexto.obtenerValorObligatorioConLongitud(
                        nombreCompleto,
                        "El nombre completo",
                        LONGITUD_MINIMA_NOMBRE,
                        LONGITUD_MAXIMA_NOMBRE
                );

        this.contacto =
                UtilTexto.obtenerValorObligatorioConLongitud(
                        contacto,
                        "El número de contacto",
                        LONGITUD_MINIMA_CONTACTO,
                        LONGITUD_MAXIMA_CONTACTO
                );

        this.correoElectronico =
                UtilTexto.obtenerValorObligatorioConLongitud(
                        correoElectronico,
                        "El correo electrónico",
                        LONGITUD_MINIMA_CORREO,
                        LONGITUD_MAXIMA_CORREO
                ).toLowerCase(Locale.ROOT);
    }

    private String validarIdentificacion(
            String identificacion) {

        return UtilTexto.obtenerValorObligatorioConLongitud(
                identificacion,
                "La identificación",
                LONGITUD_MINIMA_IDENTIFICACION,
                LONGITUD_MAXIMA_IDENTIFICACION
        );
    }

    public Long getIdCiudadano() {
        return idCiudadano;
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

    public OffsetDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}