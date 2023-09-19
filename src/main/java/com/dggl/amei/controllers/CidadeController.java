package com.dggl.amei.controllers;

import com.dggl.amei.models.Cidade;
import com.dggl.amei.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @GetMapping
    public ResponseEntity<List<Cidade>> findall(){
        List<Cidade> listaCidades = service.findAll();
        return ResponseEntity.ok().body(listaCidades);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id){
        Cidade obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
