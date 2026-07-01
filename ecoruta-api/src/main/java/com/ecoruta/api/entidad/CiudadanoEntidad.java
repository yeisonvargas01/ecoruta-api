package com.ecoruta.api.entidad;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudadano", schema = "ecoruta")
public class CiudadanoEntidad {

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
        // Constructor requerido por JPA
    }

    public CiudadanoEntidad(
            String nombreCompleto,
            String identificacion,
            String contacto,
            String correoElectronico) {

        this.nombreCompleto = nombreCompleto;
        this.identificacion = identificacion;
        this.contacto = contacto;
        this.correoElectronico = correoElectronico;
    }

    public Long getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(Long idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public OffsetDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}