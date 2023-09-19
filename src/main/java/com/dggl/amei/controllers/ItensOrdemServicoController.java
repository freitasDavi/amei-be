package com.dggl.amei.controllers;

import com.dggl.amei.models.ItensOrdemServico;
import com.dggl.amei.services.ItensOrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/itensordemservico")
public class ItensOrdemServicoController {

    @Autowired
    private ItensOrdemServicoService service;

    @GetMapping
    public ResponseEntity<List<ItensOrdemServico>> findAll(){
        List<ItensOrdemServico> listaItensOrdemServico = service.findAll();
        return ResponseEntity.ok().body(listaItensOrdemServico);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItensOrdemServico> findById(@PathVariable Long id){
        ItensOrdemServico obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<ItensOrdemServico> insert(@RequestBody ItensOrdemServico obj){
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
    public ResponseEntity<ItensOrdemServico> update(@PathVariable Long id, @RequestBody ItensOrdemServico obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }




}
