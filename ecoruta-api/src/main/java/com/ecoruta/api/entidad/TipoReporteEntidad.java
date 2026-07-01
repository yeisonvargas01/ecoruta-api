package com.ecoruta.api.entidad;

import com.ecoruta.api.utilitario.UtilTexto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "tipo_reporte",
    schema = "ecoruta",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_tipo_reporte_nombre",
            columnNames = "nombre_tipo"
        )
    }
)
public class TipoReporteEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_reporte")
    private Long idTipoReporte;

    @Column(
        name = "nombre_tipo",
        nullable = false,
        length = 80
    )
    private String nombreTipo;

    @Column(
        name = "descripcion_tipo",
        length = 250
    )
    private String descripcionTipo;

    @Column(
        name = "activo",
        nullable = false
    )
    private boolean activo;

    protected TipoReporteEntidad() {
        
    }

    public TipoReporteEntidad(
            String nombreTipo,
            String descripcionTipo) {

        actualizarInformacion(
                nombreTipo,
                descripcionTipo
        );

        activar();
    }

    public void actualizarInformacion(
            String nombreTipo,
            String descripcionTipo) {

        this.nombreTipo =
                UtilTexto.obtenerValorObligatorio(
                    nombreTipo,
                    "El nombre del tipo de reporte"
                );

        this.descripcionTipo =
                UtilTexto.obtenerValorOpcional(
                    descripcionTipo
                );
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public Long getIdTipoReporte() {
        return idTipoReporte;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public boolean isActivo() {
        return activo;
    }
}
