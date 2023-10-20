package com.dggl.amei.controllers;

import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ordermservico")
public class OrdermServicoController extends AbstractController {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    public ResponseEntity<List<OrdemServico>> findAll(){
        List<OrdemServico> listaOrdemServivo = service.findAll();
        return ResponseEntity.ok().body(listaOrdemServivo);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrdemServico> findById(@PathVariable Long id){
        OrdemServico obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<OrdemServico> insert(@RequestBody OrdemServico ordemServico){
        ordemServico = service.insert(ordemServico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ordemServico.getId()).toUri();
        return ResponseEntity.created(uri).body(ordemServico);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrdemServico> update(@PathVariable Long id, @RequestBody OrdemServico ordemServico){
        ordemServico = service.update(id, ordemServico);
        return ResponseEntity.ok().body(ordemServico);
    }


}
