package com.alura.foroHub.entradaDTO;

import jakarta.validation.constraints.NotBlank;

public record CursoDTO(
        @NotBlank String nombre,
        @NotBlank String categoria
) {
}
