package com.alura.foroHub.entradaDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoActualizarDTO(
        @NotNull Long id,
        String titulo,
        String mensaje
) {
}
