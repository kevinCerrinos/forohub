package com.kev.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        String solucion,
        String autor,
        LocalDateTime fechaCreacion
) {
}
