package com.ecoruta.api.dto.reporte;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CrearReporteDTO {

    @NotBlank(
        message = "El nombre completo es obligatorio"
    )
    @Size(
        min = 3,
        max = 120,
        message = "El nombre completo debe tener entre 3 y 120 caracteres"
    )
    @Pattern(
        regexp = "^[\\p{L} .'-]+$",
        message = "El nombre completo contiene caracteres no permitidos"
    )
    private String nombreCompleto;

    @NotBlank(
        message = "La identificación es obligatoria"
    )
    @Size(
        min = 5,
        max = 30,
        message = "La identificación debe tener entre 5 y 30 caracteres"
    )
    @Pattern(
        regexp = "^[A-Za-z0-9.-]+$",
        message = "La identificación solo puede contener letras, números, puntos y guiones"
    )
    private String identificacion;

    @NotBlank(
        message = "El número de contacto es obligatorio"
    )
    @Size(
        min = 7,
        max = 30,
        message = "El contacto debe tener entre 7 y 30 caracteres"
    )
    @Pattern(
        regexp = "^[0-9+()\\s-]+$",
        message = "El contacto contiene caracteres no permitidos"
    )
    private String contacto;

    @NotBlank(
        message = "El correo electrónico es obligatorio"
    )
    @Email(
        message = "El correo electrónico no tiene un formato válido"
    )
    @Size(
        max = 120,
        message = "El correo electrónico no puede superar los 120 caracteres"
    )
    private String correoElectronico;

    @NotNull(
        message = "La zona es obligatoria"
    )
    @Positive(
        message = "El identificador de la zona debe ser mayor que cero"
    )
    private Long idZona;

    @NotNull(
        message = "El tipo de reporte es obligatorio"
    )
    @Positive(
        message = "El identificador del tipo de reporte debe ser mayor que cero"
    )
    private Long idTipoReporte;

    @NotBlank(
        message = "La descripción del reporte es obligatoria"
    )
    @Size(
        min = 10,
        max = 1000,
        message = "La descripción debe tener entre 10 y 1000 caracteres"
    )
    private String descripcion;

    @Size(
        max = 200,
        message = "La dirección de referencia no puede superar los 200 caracteres"
    )
    private String direccionReferencia;

    @Size(
        max = 500,
        message = "La dirección de la evidencia no puede superar los 500 caracteres"
    )
    private String evidenciaUrl;

    public CrearReporteDTO() {
        /*
         * Constructor requerido para convertir el JSON recibido
         * en un objeto Java.
         */
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(
            String nombreCompleto) {

        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(
            String identificacion) {

        this.identificacion = identificacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(
            String contacto) {

        this.contacto = contacto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(
            String correoElectronico) {

        this.correoElectronico = correoElectronico;
    }

    public Long getIdZona() {
        return idZona;
    }

    public void setIdZona(
            Long idZona) {

        this.idZona = idZona;
    }

    public Long getIdTipoReporte() {
        return idTipoReporte;
    }

    public void setIdTipoReporte(
            Long idTipoReporte) {

        this.idTipoReporte = idTipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(
            String descripcion) {

        this.descripcion = descripcion;
    }

    public String getDireccionReferencia() {
        return direccionReferencia;
    }

    public void setDireccionReferencia(
            String direccionReferencia) {

        this.direccionReferencia = direccionReferencia;
    }

    public String getEvidenciaUrl() {
        return evidenciaUrl;
    }

    public void setEvidenciaUrl(
            String evidenciaUrl) {

        this.evidenciaUrl = evidenciaUrl;
    }
}
