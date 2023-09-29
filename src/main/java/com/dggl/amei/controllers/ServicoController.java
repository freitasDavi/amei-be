package com.dggl.amei.controllers;

import com.dggl.amei.models.Servico;
import com.dggl.amei.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @GetMapping
    public ResponseEntity<List<Servico>> findAll(){
        List<Servico> listaServico = service.findAll();
        return ResponseEntity.ok().body(listaServico);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id){
        Servico servico = service.findById(id);
        return ResponseEntity.ok().body(servico);
    }

    @PostMapping
    public ResponseEntity<Servico> insert(@RequestBody Servico servico){
        servico = service.insert(servico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(servico.getId()).toUri();
        return ResponseEntity.created(uri).body(servico);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody Servico servico){
        servico = service.update(id, servico);
        return ResponseEntity.ok().body(servico);
    }
}
