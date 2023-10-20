package com.dggl.amei.controllers;

import com.dggl.amei.models.Estado;
import com.dggl.amei.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/estado")
public class EstadoController extends AbstractController {

    @Autowired
    private EstadoService service;

    @GetMapping
    public ResponseEntity<List<Estado>> findall(){
        List<Estado> listaEstados = service.findAll();
        return ResponseEntity.ok().body(listaEstados);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id){
        Estado estado = service.findById(id);
        return ResponseEntity.ok().body(estado);
    }
}
