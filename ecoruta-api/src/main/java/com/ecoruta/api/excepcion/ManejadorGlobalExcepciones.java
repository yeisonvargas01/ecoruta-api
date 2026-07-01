package com.ecoruta.api.excepcion;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(
                    ManejadorGlobalExcepciones.class
            );

    @ExceptionHandler(
        RecursoNoEncontradoExcepcion.class
    )
    public ResponseEntity<RespuestaError>
            manejarRecursoNoEncontrado(
                    RecursoNoEncontradoExcepcion excepcion,
                    HttpServletRequest solicitud) {

        return construirRespuesta(
                HttpStatus.NOT_FOUND,
                "Recurso no encontrado",
                excepcion.getMessage(),
                solicitud.getRequestURI()
        );
    }

    @ExceptionHandler(
        ConflictoDatosExcepcion.class
    )
    public ResponseEntity<RespuestaError>
            manejarConflictoDatos(
                    ConflictoDatosExcepcion excepcion,
                    HttpServletRequest solicitud) {

        return construirRespuesta(
                HttpStatus.CONFLICT,
                "Conflicto de datos",
                excepcion.getMessage(),
                solicitud.getRequestURI()
        );
    }

    @ExceptionHandler(
        ReglaNegocioExcepcion.class
    )
    public ResponseEntity<RespuestaError>
            manejarReglaNegocio(
                    ReglaNegocioExcepcion excepcion,
                    HttpServletRequest solicitud) {

        return construirRespuesta(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Regla de negocio incumplida",
                excepcion.getMessage(),
                solicitud.getRequestURI()
        );
    }

    @ExceptionHandler(
        MethodArgumentNotValidException.class
    )
    public ResponseEntity<RespuestaError>
            manejarValidacionCampos(
                    MethodArgumentNotValidException excepcion,
                    HttpServletRequest solicitud) {

        String mensaje =
                obtenerMensajesValidacion(excepcion);

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "Datos de entrada inválidos",
                mensaje,
                solicitud.getRequestURI()
        );
    }

    @ExceptionHandler(
        HttpMessageNotReadableException.class
    )
    public ResponseEntity<RespuestaError>
            manejarJsonNoLegible(
                    HttpMessageNotReadableException excepcion,
                    HttpServletRequest solicitud) {

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "Solicitud inválida",
                "El cuerpo de la solicitud está vacío, "
                + "mal escrito o contiene tipos de datos incorrectos",
                solicitud.getRequestURI()
        );
    }

    @ExceptionHandler(
        DataIntegrityViolationException.class
    )
    public ResponseEntity<RespuestaError>
            manejarIntegridadDatos(
                    DataIntegrityViolationException excepcion,
                    HttpServletRequest solicitud) {

        LOGGER.warn(
                "Conflicto de integridad de datos en la ruta {}",
                solicitud.getRequestURI()
        );

        return construirRespuesta(
                HttpStatus.CONFLICT,
                "Conflicto de datos",
                "La operación entra en conflicto con "
                + "información existente en el sistema",
                solicitud.getRequestURI()
        );
    }

    @ExceptionHandler(
        IllegalArgumentException.class
    )
    public ResponseEntity<RespuestaError>
            manejarArgumentoInvalido(
                    IllegalArgumentException excepcion,
                    HttpServletRequest solicitud) {

        return construirRespuesta(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Datos no permitidos",
                excepcion.getMessage(),
                solicitud.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError>
            manejarErrorInesperado(
                    Exception excepcion,
                    HttpServletRequest solicitud) {

        LOGGER.error(
                "Error inesperado en la ruta {}",
                solicitud.getRequestURI(),
                excepcion
        );

        return construirRespuesta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor",
                "Ocurrió un error inesperado. "
                + "Por favor, inténtalo nuevamente más tarde",
                solicitud.getRequestURI()
        );
    }

    private String obtenerMensajesValidacion(
            MethodArgumentNotValidException excepcion) {

        String mensajes =
                excepcion.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .sorted(
                            Comparator.comparing(
                                FieldError::getField
                            )
                        )
                        .map(this::construirMensajeCampo)
                        .distinct()
                        .collect(
                            Collectors.joining("; ")
                        );

        if (mensajes.isBlank()) {
            return "La solicitud contiene datos inválidos";
        }

        return mensajes;
    }

    private String construirMensajeCampo(
            FieldError errorCampo) {

        String mensaje =
                errorCampo.getDefaultMessage();

        if (mensaje == null
                || mensaje.isBlank()) {

            mensaje = "El valor ingresado no es válido";
        }

        return errorCampo.getField()
                + ": "
                + mensaje;
    }

    private ResponseEntity<RespuestaError>
            construirRespuesta(
                    HttpStatus estadoHttp,
                    String error,
                    String mensaje,
                    String ruta) {

        RespuestaError respuesta =
                new RespuestaError(
                        estadoHttp.value(),
                        error,
                        mensaje,
                        ruta,
                        OffsetDateTime.now()
                );

        return ResponseEntity
                .status(estadoHttp)
                .body(respuesta);
    }
}