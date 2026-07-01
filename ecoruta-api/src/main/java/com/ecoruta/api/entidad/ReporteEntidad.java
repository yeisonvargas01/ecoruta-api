package com.ecoruta.api.entidad;

import java.time.OffsetDateTime;

import com.ecoruta.api.utilitario.UtilObjeto;
import com.ecoruta.api.utilitario.UtilTexto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "reporte",
    schema = "ecoruta"
)
public class ReporteEntidad {

    private static final int LONGITUD_MINIMA_DESCRIPCION = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Long idReporte;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_ciudadano",
        nullable = false,
        foreignKey = @ForeignKey(
            name = "fk_reporte_ciudadano"
        )
    )
    private CiudadanoEntidad ciudadano;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_zona",
        nullable = false,
        foreignKey = @ForeignKey(
            name = "fk_reporte_zona"
        )
    )
    private ZonaEntidad zona;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_estado",
        nullable = false,
        foreignKey = @ForeignKey(
            name = "fk_reporte_estado"
        )
    )
    private EstadoReporteEntidad estadoReporte;

    @ManyToOne(
        fetch = FetchType.LAZY,
        optional = false
    )
    @JoinColumn(
        name = "id_tipo_reporte",
        nullable = false,
        foreignKey = @ForeignKey(
            name = "fk_reporte_tipo"
        )
    )
    private TipoReporteEntidad tipoReporte;

    @Column(
        name = "descripcion",
        nullable = false
    )
    private String descripcion;

    @Column(
        name = "direccion_referencia",
        length = 200
    )
    private String direccionReferencia;

    @Column(
        name = "evidencia_url",
        length = 500
    )
    private String evidenciaUrl;

    @Column(name = "observacion_respuesta")
    private String observacionRespuesta;

    @Column(
        name = "fecha_reporte",
        nullable = false,
        updatable = false
    )
    private OffsetDateTime fechaReporte;

    protected ReporteEntidad() {
        
    }

    public ReporteEntidad(
            CiudadanoEntidad ciudadano,
            ZonaEntidad zona,
            EstadoReporteEntidad estadoReporte,
            TipoReporteEntidad tipoReporte,
            String descripcion,
            String direccionReferencia,
            String evidenciaUrl) {

        asignarCiudadano(ciudadano);
        asignarZona(zona);
        cambiarEstado(estadoReporte);
        asignarTipoReporte(tipoReporte);

        actualizarInformacion(
                descripcion,
                direccionReferencia,
                evidenciaUrl
        );
    }

    public void actualizarInformacion(
            String descripcion,
            String direccionReferencia,
            String evidenciaUrl) {

        this.descripcion = validarDescripcion(descripcion);

        this.direccionReferencia =
                UtilTexto.obtenerValorOpcional(
                    direccionReferencia
                );

        this.evidenciaUrl =
                UtilTexto.obtenerValorOpcional(
                    evidenciaUrl
                );
    }

    public void asignarCiudadano(
            CiudadanoEntidad ciudadano) {

        this.ciudadano =
                UtilObjeto.obtenerValorObligatorio(
                    ciudadano,
                    "El ciudadano"
                );
    }

    public void asignarZona(
            ZonaEntidad zona) {

        this.zona =
                UtilObjeto.obtenerValorObligatorio(
                    zona,
                    "La zona"
                );
    }

    public void asignarTipoReporte(
            TipoReporteEntidad tipoReporte) {

        this.tipoReporte =
                UtilObjeto.obtenerValorObligatorio(
                    tipoReporte,
                    "El tipo de reporte"
                );
    }

    public void cambiarEstado(
            EstadoReporteEntidad estadoReporte) {

        this.estadoReporte =
                UtilObjeto.obtenerValorObligatorio(
                    estadoReporte,
                    "El estado del reporte"
                );
    }

    public void registrarObservacionRespuesta(
            String observacionRespuesta) {

        this.observacionRespuesta =
                UtilTexto.obtenerValorOpcional(
                    observacionRespuesta
                );
    }

    private String validarDescripcion(
            String descripcion) {

        String valorNormalizado =
                UtilTexto.obtenerValorObligatorio(
                    descripcion,
                    "La descripción del reporte"
                );

        if (valorNormalizado.length()
                < LONGITUD_MINIMA_DESCRIPCION) {

            throw new IllegalArgumentException(
                "La descripción del reporte debe tener mínimo "
                + LONGITUD_MINIMA_DESCRIPCION
                + " caracteres"
            );
        }

        return valorNormalizado;
    }

    @PrePersist
    private void asignarFechaReporte() {

        if (fechaReporte == null) {
            fechaReporte = OffsetDateTime.now();
        }
    }

    public Long getIdReporte() {
        return idReporte;
    }

    public CiudadanoEntidad getCiudadano() {
        return ciudadano;
    }

    public ZonaEntidad getZona() {
        return zona;
    }

    public EstadoReporteEntidad getEstadoReporte() {
        return estadoReporte;
    }

    public TipoReporteEntidad getTipoReporte() {
        return tipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDireccionReferencia() {
        return direccionReferencia;
    }

    public String getEvidenciaUrl() {
        return evidenciaUrl;
    }

    public String getObservacionRespuesta() {
        return observacionRespuesta;
    }

    public OffsetDateTime getFechaReporte() {
        return fechaReporte;
    }
}
