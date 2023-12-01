package com.dggl.amei.services;

import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Bairro;
import com.dggl.amei.repositories.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    private BairroRepository repository;
    private String task = "Bairro";

    public Page<Bairro> findAll(String filter, Pageable pageable){
        return repository.findAll(filter, Bairro.class, pageable);
    }

    public Bairro findById(Long id){
        Optional<Bairro> bairro = repository.findById(id);
        return bairro.orElseThrow(()-> new RecursoNaoEncontrado(task, id));
    }
}
