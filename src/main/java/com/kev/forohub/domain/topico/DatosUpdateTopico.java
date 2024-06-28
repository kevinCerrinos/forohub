package com.kev.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosUpdateTopico(
        String titulo,
        String mensaje,
        Status status,
        LocalDateTime fechaActualizacion
) {
}
