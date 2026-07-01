package com.ecoruta.api.entidad;

import java.util.Set;

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
    name = "zona",
    schema = "ecoruta",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_zona_nombre_municipio",
            columnNames = {
                "nombre_zona",
                "municipio"
            }
        )
    }
)
public class ZonaEntidad {

    private static final Set<String> TIPOS_ZONA_VALIDOS =
            Set.of(
                "barrio",
                "vereda",
                "centro"
            );

    private static final Set<String> SECTORES_VALIDOS =
            Set.of(
                "norte",
                "sur",
                "centro",
                "occidente"
            );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Long idZona;

    @Column(
        name = "nombre_zona",
        nullable = false,
        length = 120
    )
    private String nombreZona;

    @Column(
        name = "tipo_zona",
        nullable = false,
        length = 30
    )
    private String tipoZona;

    @Column(
        name = "sector",
        nullable = false,
        length = 30
    )
    private String sector;

    @Column(
        name = "municipio",
        nullable = false,
        length = 80
    )
    private String municipio;

    @Column(
        name = "activo",
        nullable = false
    )
    private boolean activo;

    @Column(
        name = "horario",
        nullable = false,
        length = 80
    )
    private String horario;

    protected ZonaEntidad() {
        /*
         * Constructor sin argumentos requerido por JPA.
         */
    }

    public ZonaEntidad(
            String nombreZona,
            String tipoZona,
            String sector,
            String municipio,
            String horario) {

        actualizarInformacion(
                nombreZona,
                tipoZona,
                sector,
                municipio,
                horario
        );

        activar();
    }

    public void actualizarInformacion(
            String nombreZona,
            String tipoZona,
            String sector,
            String municipio,
            String horario) {

        this.nombreZona =
                UtilTexto.obtenerValorObligatorio(
                    nombreZona,
                    "El nombre de la zona"
                );

        this.tipoZona = validarTipoZona(tipoZona);

        this.sector = validarSector(sector);

        this.municipio =
                UtilTexto.obtenerValorObligatorio(
                    municipio,
                    "El municipio"
                );

        this.horario =
                UtilTexto.obtenerValorObligatorio(
                    horario,
                    "El horario"
                );
    }

    private String validarTipoZona(String tipoZona) {

        String valorNormalizado =
                UtilTexto.obtenerValorObligatorioEnMinuscula(
                    tipoZona,
                    "El tipo de zona"
                );

        if (!TIPOS_ZONA_VALIDOS.contains(valorNormalizado)) {
            throw new IllegalArgumentException(
                "El tipo de zona debe ser barrio, vereda o centro"
            );
        }

        return valorNormalizado;
    }

    private String validarSector(String sector) {

        String valorNormalizado =
                UtilTexto.obtenerValorObligatorioEnMinuscula(
                    sector,
                    "El sector"
                );

        if (!SECTORES_VALIDOS.contains(valorNormalizado)) {
            throw new IllegalArgumentException(
                "El sector debe ser norte, sur, centro u occidente"
            );
        }

        return valorNormalizado;
    }

    public void activar() {
        this.activo = true;
    }

    public void desactivar() {
        this.activo = false;
    }

    public Long getIdZona() {
        return idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    public String getSector() {
        return sector;
    }

    public String getMunicipio() {
        return municipio;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getHorario() {
        return horario;
    }
}
