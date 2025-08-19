package com.alura.foroHub.controller;

import com.alura.foroHub.entity.Topico;
import com.alura.foroHub.entity.Usuario;
import com.alura.foroHub.entradaDTO.TopicoActualizarDTO;
import com.alura.foroHub.entradaDTO.TopicoDTO;
import com.alura.foroHub.repository.CursoRepository;
import com.alura.foroHub.repository.TopicoRepository;
import com.alura.foroHub.salidaDTO.TopicoSalidaDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid TopicoDTO topicoDTO, UriComponentsBuilder uriComponentsBuilder){

        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var curso = cursoRepository.findById(topicoDTO.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        var topico = new Topico(topicoDTO, usuario, curso);

        topicoRepository.save(topico);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoSalidaDTO(topico));
    };

    @GetMapping
    public ResponseEntity<Page<TopicoSalidaDTO>> listarTopicos(
            @PageableDefault(size=10) Pageable pag){
        var page = topicoRepository.findAllByEstadoTrue(pag)
                .map(TopicoSalidaDTO::new);
        return ResponseEntity.ok(page);
    };

    @GetMapping("/{id}")
    public ResponseEntity<TopicoSalidaDTO> obtenerUnTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        var resultado = new TopicoSalidaDTO(topico);
        return ResponseEntity.ok(resultado);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid TopicoActualizarDTO topicoDTO){
        var topico = topicoRepository.getReferenceById(topicoDTO.id());
        topico.actualizarDatos(topicoDTO);

        return ResponseEntity.ok(new TopicoSalidaDTO(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
