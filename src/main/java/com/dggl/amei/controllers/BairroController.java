package com.dggl.amei.controllers;

import com.dggl.amei.models.Bairro;
import com.dggl.amei.services.BairroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/bairro")
public class BairroController {

    @Autowired
    private BairroService service;

    @GetMapping
    public ResponseEntity<List<Bairro>> findall(){
        List<Bairro> listaBairros = service.findAll();
        return ResponseEntity.ok().body(listaBairros);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bairro> findById(@PathVariable Long id){
        Bairro bairro = service.findById(id);
        return ResponseEntity.ok().body(bairro);
    }
}
