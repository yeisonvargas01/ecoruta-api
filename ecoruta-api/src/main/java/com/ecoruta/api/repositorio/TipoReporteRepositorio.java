package com.ecoruta.api.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoruta.api.entidad.TipoReporteEntidad;

public interface TipoReporteRepositorio
        extends JpaRepository<TipoReporteEntidad, Long> {

    List<TipoReporteEntidad>
            findByActivoTrueOrderByNombreTipoAsc();

    Optional<TipoReporteEntidad>
            findByIdTipoReporteAndActivoTrue(
                    Long idTipoReporte
            );
}
