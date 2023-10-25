package com.dggl.amei.controllers;

import com.dggl.amei.dtos.requests.NovoServicoRequest;
import com.dggl.amei.models.Servico;
import com.dggl.amei.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/servicos")
public class ServicoController extends AbstractController {

    @Autowired
    private ServicoService service;

    @GetMapping
    public ResponseEntity<List<Servico>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Servico> listaServico = service.findAll(filter, PageRequest.of(page, size));
        return ResponseEntity.ok().body(listaServico.getContent());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Servico> findById(@PathVariable Long id){
        Servico servico = service.findById(id);
        return ResponseEntity.ok().body(servico);
    }

    @PostMapping
    public ResponseEntity<Servico> insert(@RequestBody NovoServicoRequest dto){
        var novoServico = service.insert(dto);

        return ResponseEntity.ok(novoServico);
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
