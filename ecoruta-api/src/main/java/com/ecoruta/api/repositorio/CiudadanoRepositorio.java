package com.ecoruta.api.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecoruta.api.entidad.CiudadanoEntidad;

public interface CiudadanoRepositorio
        extends JpaRepository<CiudadanoEntidad, Long> {

    Optional<CiudadanoEntidad> findByIdentificacion(
            String identificacion
    );

    Optional<CiudadanoEntidad> findByCorreoElectronicoIgnoreCase(
            String correoElectronico
    );
}