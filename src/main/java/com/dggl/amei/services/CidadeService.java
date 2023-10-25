package com.dggl.amei.services;

import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;
    private String task = "Cidade";

    public Page<Cidade> findAll(String filter, Pageable pageable){

        return repository.findAll(filter, Cidade.class, pageable);
    }

    public Cidade findById(Long id){
        Optional<Cidade> cidade = repository.findById(id);
        return cidade.orElseThrow(()-> new RecursoNaoEncontrado(task, id));
    }


}
