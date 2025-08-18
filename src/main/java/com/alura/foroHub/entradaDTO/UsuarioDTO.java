package com.alura.foroHub.entradaDTO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank String email,
        @NotBlank String password
) {
}
