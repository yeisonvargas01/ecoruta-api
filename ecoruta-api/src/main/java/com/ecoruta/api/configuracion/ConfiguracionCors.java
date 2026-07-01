package com.ecoruta.api.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ecoruta.api.utilitario.UtilObjeto;

@Configuration
public class ConfiguracionCors
        implements WebMvcConfigurer {

    private static final String RUTA_API =
            "/api/**";

    private static final String[] ORIGENES_PERMITIDOS = {
        "http://localhost:5500",
        "http://127.0.0.1:5500",
        "https://ecorutaciudadana.cloud",
        "https://www.ecorutaciudadana.cloud"
    };

    private static final String[] METODOS_PERMITIDOS = {
        "GET",
        "POST",
        "PATCH",
        "OPTIONS"
    };

    private static final long DURACION_CACHE_SEGUNDOS =
            3600L;

    @Override
    public void addCorsMappings(
            CorsRegistry registro) {

        CorsRegistry registroValidado =
                UtilObjeto.obtenerValorObligatorio(
                        registro,
                        "El registro de configuración CORS"
                );

        registroValidado
                .addMapping(RUTA_API)
                .allowedOrigins(ORIGENES_PERMITIDOS)
                .allowedMethods(METODOS_PERMITIDOS)
                .allowedHeaders(
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.ACCEPT
                )
                .allowCredentials(false)
                .maxAge(DURACION_CACHE_SEGUNDOS);
    }
}
