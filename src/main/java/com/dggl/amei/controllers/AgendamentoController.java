package com.dggl.amei.controllers;

import com.dggl.amei.models.Agendamento;
import com.dggl.amei.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/agendamentos")
public class AgendamentoController extends AbstractController {

    @Autowired
    private AgendamentoService service;

    @GetMapping
    public ResponseEntity<List<Agendamento>> findAll(){
        List<Agendamento> listaAgendamento = service.findAll();
        return ResponseEntity.ok().body(listaAgendamento);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Agendamento> findById(@PathVariable Long id){
        Agendamento obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Agendamento> insert(@RequestBody Agendamento obj){
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
    public ResponseEntity<Agendamento> update(@PathVariable Long id, @RequestBody Agendamento obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
