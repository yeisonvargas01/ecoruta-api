package com.ecoruta.api.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoruta.api.entidad.ZonaEntidad;

public interface ZonaRepositorio
        extends JpaRepository<ZonaEntidad, Long> {

    List<ZonaEntidad> findByActivoTrueOrderByNombreZonaAsc();

    Optional<ZonaEntidad> findByIdZonaAndActivoTrue(
            Long idZona
    );
}
