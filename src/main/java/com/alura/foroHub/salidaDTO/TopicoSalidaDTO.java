package com.alura.foroHub.salidaDTO;

import com.alura.foroHub.entity.Topico;
import com.alura.foroHub.entity.Usuario;
import com.alura.foroHub.entradaDTO.UsuarioDTO;

import java.time.LocalDateTime;

public record TopicoSalidaDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String nombreAutor,
        String nombreCurso
) {

    public TopicoSalidaDTO(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
