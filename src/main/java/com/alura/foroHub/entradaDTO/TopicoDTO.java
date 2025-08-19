package com.alura.foroHub.entradaDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDTO(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long cursoId

) {
}
