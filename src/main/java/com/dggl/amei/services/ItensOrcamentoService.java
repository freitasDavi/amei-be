package com.dggl.amei.services;

import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.repositories.ItensOrcamentoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ItensOrcamentoService {

    @Autowired
    private ItensOrcamentoRepository repository;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public List<ItensOrcamento> findAll() {
        return repository.findAll();
    }

    public ItensOrcamento findById(Long id) {
        Optional<ItensOrcamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontrado(id));
    }

    public ItensOrcamento insert(ItensOrcamento obj) {
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


    public ItensOrcamento update(Long id, ItensOrcamento obj) {
        try {
            ItensOrcamento entity = repository.getReferenceById(id);
            updateDados(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado(id);
        }
    }

    private void updateDados(ItensOrcamento entity, ItensOrcamento obj){
        entity.setDescricao(obj.getDescricao());
        entity.setValorUnitario(obj.getValorUnitario());
        entity.setValorTotal(obj.getValorTotal());
        entity.setOrcamentoItens(obj.getOrcamentoItens());



    }
}
