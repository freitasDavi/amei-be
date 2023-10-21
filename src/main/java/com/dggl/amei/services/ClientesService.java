package com.dggl.amei.services;

import com.dggl.amei.models.Clientes;
import com.dggl.amei.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    public Page<Clientes> findAll(String filter, Pageable pageable){
        return repository.findAll(filter, Clientes.class, pageable);
    }
}
