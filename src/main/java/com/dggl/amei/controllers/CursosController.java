package com.dggl.amei.controllers;

import com.dggl.amei.dtos.responses.CursoResponseDTO;
import com.dggl.amei.models.Curso;
import com.dggl.amei.services.CursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cursos")
public class CursosController extends AbstractController {

    @Autowired
    private CursosService service;

    @GetMapping
    public ResponseEntity<Page<CursoResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Curso> cursos = service.findAll(filter, PageRequest.of(page, size));

        Page<CursoResponseDTO> cursosDTO = CursoResponseDTO.fromEntity(cursos);

        return ResponseEntity.ok().body(cursosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CursoResponseDTO> findById (@PathVariable Long id) {
        var curso = service.findById(id);

        CursoResponseDTO responseDTO = CursoResponseDTO.fromEntity(curso);

        return  ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity createNewCurso(@RequestBody CursoResponseDTO dto) {
        service.insert(dto);

        return ResponseEntity.ok("Sucessagem");
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody CursoResponseDTO dto) {
        service.update(id, dto);

        return ResponseEntity.ok("Sucessagem");
    }
}
