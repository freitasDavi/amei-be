package com.dggl.amei.controllers;

import com.dggl.amei.configuration.security.services.UserDetailsImpl;
import com.dggl.amei.dtos.requests.NovoServicoRequest;
import com.dggl.amei.dtos.requests.UpdateServicoRequest;
import com.dggl.amei.models.Servico;
import com.dggl.amei.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Page<Servico>> findAll(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication
    ){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Page<Servico> listaServico = service.findAll(filter, PageRequest.of(page, size), userDetails.getId());
        return ResponseEntity.ok().body(listaServico);
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
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateServicoRequest servico){
        service.update(id, servico);
        return ResponseEntity.ok().body("Registro atualizado com sucesso");
    }
}
