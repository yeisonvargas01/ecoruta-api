package com.ecoruta.api.negocio.casouso;

import java.util.List;

import com.ecoruta.api.dto.zona.ZonaRespuestaDTO;

public interface ConsultarZonasCasoUso {

    List<ZonaRespuestaDTO> ejecutar();
}
