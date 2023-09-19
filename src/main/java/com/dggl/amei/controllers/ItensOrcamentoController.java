package com.dggl.amei.controllers;

import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.services.ItensOrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/itensorcamento")
public class ItensOrcamentoController {

    @Autowired
    private ItensOrcamentoService service;

    @GetMapping
    public ResponseEntity<List<ItensOrcamento>> findAll(){
        List<ItensOrcamento> listaItensOrcamento = service.findAll();
        return ResponseEntity.ok().body(listaItensOrcamento);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItensOrcamento> findById(@PathVariable Long id){
        ItensOrcamento obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<ItensOrcamento> insert(@RequestBody ItensOrcamento obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItensOrcamento> update(@PathVariable Long id, @RequestBody ItensOrcamento obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }




}
