package com.dggl.amei.controllers;

import com.dggl.amei.dtos.responses.CursoResponseDTO;
import com.dggl.amei.models.Curso;
import com.dggl.amei.services.CursosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    ///@GetMapping(value = "/{id}")
}
