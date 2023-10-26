package com.dggl.amei.controllers;

import com.dggl.amei.dtos.requests.AgendamentoRequestDTO;
import com.dggl.amei.dtos.responses.AgendamentoResponseDTO;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/agendamentos")
public class AgendamentoController extends AbstractController {

    @Autowired
    private AgendamentoService service;

    @GetMapping
    public ResponseEntity<Page<AgendamentoResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Agendamento> agendamentos = service.findAll(filter, PageRequest.of(page, size));

        return ResponseEntity.ok().body(AgendamentoResponseDTO.fromEntity(agendamentos));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoResponseDTO> findById(@PathVariable Long id){
        Agendamento obj = service.findById(id);

        return ResponseEntity.ok().body(AgendamentoResponseDTO.fromEntity(obj));
    }

    @PostMapping
    public ResponseEntity<Agendamento> insert(@RequestBody AgendamentoRequestDTO obj){
        service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
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
