package com.kev.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        String solucion,
        String autor,
        String topico,
        LocalDateTime fechaCreacion
) {
}
