package com.dggl.amei.services;

import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;
    private String task = "Cidade";

    public List<Cidade> findAll(){
        return repository.findAll();
    }

    public Cidade findById(Long id){
        Optional<Cidade> cidade = repository.findById(id);
        return cidade.orElseThrow(()-> new RecursoNaoEncontrado(task, id));
    }


}
