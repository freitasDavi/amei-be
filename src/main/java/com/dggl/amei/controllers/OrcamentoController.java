package com.dggl.amei.controllers;

import com.dggl.amei.models.Orcamento;
import com.dggl.amei.services.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService service;

    @GetMapping
    public ResponseEntity<List<Orcamento>> findAll(){
        List<Orcamento> listaOrcamento = service.findAll();
        return ResponseEntity.ok().body(listaOrcamento);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Orcamento> findById(@PathVariable Long id){
        Orcamento obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Orcamento> insert(@RequestBody Orcamento orcamento){
        orcamento = service.insert(orcamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orcamento.getId()).toUri();
        return ResponseEntity.created(uri).body(orcamento);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Orcamento> update(@PathVariable Long id, @RequestBody Orcamento orcamento){
        orcamento = service.update(id, orcamento);
        return ResponseEntity.ok().body(orcamento);
    }

}
