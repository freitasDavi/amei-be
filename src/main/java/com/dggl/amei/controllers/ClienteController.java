package com.dggl.amei.controllers;

import com.dggl.amei.dtos.responses.ClientsResponseDTO;
import com.dggl.amei.models.Clientes;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesService service;

    @GetMapping
    public ResponseEntity<Page<ClientsResponseDTO>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Clientes> clientes = service.findAll(filter, PageRequest.of(page, size));

        Page<ClientsResponseDTO> clientesDTO = ClientsResponseDTO.fromEntity(clientes);

        return ResponseEntity.ok().body(clientesDTO);
    }
}
