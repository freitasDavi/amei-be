package com.dggl.amei.controllers;

import com.dggl.amei.models.Cidade;
import com.dggl.amei.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cidade")
public class CidadeController extends AbstractController {

    @Autowired
    private CidadeService service;

    @GetMapping
    public ResponseEntity<Page<Cidade>> findall(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Cidade> listaCidades = service.findAll(filter, PageRequest.of(page, size));

        return ResponseEntity.ok().body(listaCidades);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id){
        Cidade cidade = service.findById(id);
        return ResponseEntity.ok().body(cidade);
    }
}
