package com.dggl.amei.services;

import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.ItensOrdemServico;
import com.dggl.amei.repositories.ItensOrdemServicoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ItensOrdemServicoService {

    @Autowired
    private ItensOrdemServicoRepository repository;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public List<ItensOrdemServico> findAll() {
        return repository.findAll();
    }

    public ItensOrdemServico findById(Long id) {
        Optional<ItensOrdemServico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontrado(id));
    }

    public ItensOrdemServico insert(ItensOrdemServico obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontrado(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }


    public ItensOrdemServico update(Long id, ItensOrdemServico obj) {
        try {
            ItensOrdemServico entity = repository.getReferenceById(id);
            updateDados(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado(id);
        }
    }
    
    private void updateDados(ItensOrdemServico entity, ItensOrdemServico obj){
        entity.setValorUnitario(obj.getValorUnitario());
        entity.setValorTotal(obj.getValorTotal());
        entity.setValorUnitario(obj.getValorUnitario());
        entity.setDescricaoItemOrdem(obj.getDescricaoItemOrdem());
        entity.setOrdemItens(obj.getOrdemItens());
        
        
        
    }
}
