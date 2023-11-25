package com.dggl.amei.controllers;

import com.dggl.amei.dtos.responses.ClienteResponseDTO;
import com.dggl.amei.dtos.responses.ClientsComboResponseDTO;
import com.dggl.amei.models.Clientes;
import com.dggl.amei.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesService service;

    @GetMapping("/combo")
    public ResponseEntity<Page<ClientsComboResponseDTO>> findAllCombo(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Clientes> clientes = service.findAll(filter, PageRequest.of(page, size));

        Page<ClientsComboResponseDTO> clientesDTO = ClientsComboResponseDTO.fromEntity(clientes);

        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping()
    public ResponseEntity<Page<ClienteResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Clientes> clientes = service.findAll(filter, PageRequest.of(page, size));

        Page<ClienteResponseDTO> clientesDTO = ClienteResponseDTO.fromEntity(clientes);

        return ResponseEntity.ok().body(clientesDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> findById(@PathVariable Long id) {
        var res = service.findById(id);

        if (!res.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ClienteResponseDTO.fromEntity(res.get()));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ClienteResponseDTO dto) {
        service.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> update (@PathVariable Long id, @RequestBody ClienteResponseDTO dto) {
        var res = service.update(id, dto);

        return ResponseEntity.ok(ClienteResponseDTO.fromEntity(res));
    }
}
