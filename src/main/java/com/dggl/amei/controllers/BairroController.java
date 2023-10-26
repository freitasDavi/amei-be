package com.dggl.amei.controllers;

import com.dggl.amei.dtos.responses.BairrosResponseDTO;
import com.dggl.amei.models.Bairro;
import com.dggl.amei.services.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/bairros")
public class BairroController extends AbstractController {

    @Autowired
    private BairroService service;

    @GetMapping
    public ResponseEntity<Page<BairrosResponseDTO>> findall(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Bairro> listaBairros = service.findAll(filter, PageRequest.of(page, size));

        return ResponseEntity.ok().body(BairrosResponseDTO.fromEntity(listaBairros));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bairro> findById(@PathVariable Long id){
        Bairro bairro = service.findById(id);
        return ResponseEntity.ok().body(bairro);
    }
}
