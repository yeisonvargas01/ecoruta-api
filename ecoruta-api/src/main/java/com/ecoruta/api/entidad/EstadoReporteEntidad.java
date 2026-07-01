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
    name = "estado_reporte",
    schema = "ecoruta",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_estado_reporte_nombre",
            columnNames = "nombre_estado"
        )
    }
)
public class EstadoReporteEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long idEstado;

    @Column(
        name = "nombre_estado",
        nullable = false,
        unique = true,
        length = 40
    )
    private String nombreEstado;

    @Column(
        name = "descripcion_estado",
        length = 250
    )
    private String descripcionEstado;

    @Column(
        name = "activo",
        nullable = false
    )
    private boolean activo;

    protected EstadoReporteEntidad() {
        /*
         * Constructor sin argumentos requerido por JPA.
         */
    }

    public EstadoReporteEntidad(
            String nombreEstado,
            String descripcionEstado) {

        actualizarInformacion(
                nombreEstado,
                descripcionEstado
        );

        activar();
    }

    public void actualizarInformacion(
            String nombreEstado,
            String descripcionEstado) {

        this.nombreEstado =
                UtilTexto.obtenerValorObligatorio(
                    nombreEstado,
                    "El nombre del estado"
                );

        this.descripcionEstado =
                UtilTexto.obtenerValorOpcional(
                    descripcionEstado
                );
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public boolean isActivo() {
        return activo;
    }
}
