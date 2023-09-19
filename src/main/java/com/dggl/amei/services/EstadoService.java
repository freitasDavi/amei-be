package com.dggl.amei.services;

import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Estado;
import com.dggl.amei.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> findAll(){
        return repository.findAll();
    }

    public Estado findById(Long id){
        Optional<Estado> obj = repository.findById(id);
        return obj.orElseThrow(()-> new RecursoNaoEncontrado(id));
    }

}
