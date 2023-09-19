package com.dggl.amei.services;

import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

    public List<OrdemServico> findAll(){
        return repository.findAll();
    }

    public OrdemServico findById(Long id){
        Optional<OrdemServico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontrado(id));
    }

    public OrdemServico insert(OrdemServico obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new RecursoNaoEncontrado(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public OrdemServico update(Long id, OrdemServico obj){
        try {
            OrdemServico entity = repository.getReferenceById(id);
            updateDados(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(id);
        }
    }

    public void updateDados(OrdemServico entity, OrdemServico obj){
        entity.setTelefoneOrdem(obj.getTelefoneOrdem());
        entity.setValorTotal(obj.getValorTotal());
    }



}
