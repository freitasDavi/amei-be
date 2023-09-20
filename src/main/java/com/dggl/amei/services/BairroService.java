package com.dggl.amei.services;

import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Bairro;
import com.dggl.amei.repositories.BairroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BairroService {

    @Autowired
    private BairroRepository repository;

    public List<Bairro> findAll(){
        return repository.findAll();
    }

    public Bairro findById(Long id){
        Optional<Bairro> bairro = repository.findById(id);
        return bairro.orElseThrow(()-> new RecursoNaoEncontrado(id));
    }
}
