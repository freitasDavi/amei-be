package com.dggl.amei.controllers;

import com.dggl.amei.dtos.responses.OrdemServicoResponseDTO;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ordemServico")
public class OrdermServicoController extends AbstractController {

    @Autowired
    private OrdemServicoService service;

    @GetMapping
    public ResponseEntity<Page<OrdemServicoResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<OrdemServico> listaOrdemServivo = service.findAll(filter, PageRequest.of(page, size));

        return ResponseEntity.ok().body(OrdemServicoResponseDTO.fromEntity(listaOrdemServivo));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> findById(@PathVariable Long id){
        OrdemServico obj = service.findById(id);

        return ResponseEntity.ok().body(OrdemServicoResponseDTO.fromEntity(obj));
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
