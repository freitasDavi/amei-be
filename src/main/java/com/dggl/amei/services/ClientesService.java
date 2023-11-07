package com.dggl.amei.services;

import com.dggl.amei.dtos.responses.ClienteResponseDTO;
import com.dggl.amei.models.Clientes;
import com.dggl.amei.models.QClientes;
import com.dggl.amei.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    public Optional<Clientes> findById(Long id) {
        return repository.findById(id);
    }

    public Page<Clientes> findAll(String filter, Pageable pageable){



        return repository.findAll(filter, Clientes.class, pageable);
    }

    public void create (ClienteResponseDTO dto) {
        var cliente = dto.toEntity();

        repository.save(cliente);
    }
}
