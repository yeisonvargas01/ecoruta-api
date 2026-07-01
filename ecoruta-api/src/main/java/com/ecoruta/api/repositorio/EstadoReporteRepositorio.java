package com.ecoruta.api.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoruta.api.entidad.EstadoReporteEntidad;

public interface EstadoReporteRepositorio
        extends JpaRepository<EstadoReporteEntidad, Long> {

    Optional<EstadoReporteEntidad>
            findByNombreEstadoIgnoreCaseAndActivoTrue(
                    String nombreEstado
            );
}
