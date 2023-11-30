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
    private String taskName = "Item do Orçamento";

    public List<ItensOrcamento> findAll() {
        return repository.findAll();
    }

    public ItensOrcamento findById(Long id) {
        Optional<ItensOrcamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }

    public ItensOrcamento insert(ItensOrcamento itensOrcamento) {
        return repository.save(itensOrcamento);
    }

    public void delete(Long id) {
        try {
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontrado(taskName, id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }


    public ItensOrcamento update(Long id, ItensOrcamento novoItensOrcamento) {
        try {
            ItensOrcamento itensOrcamentoBanco = repository.getReferenceById(id);
            updateDados(itensOrcamentoBanco, novoItensOrcamento);
            return repository.save(itensOrcamentoBanco);
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado(taskName, id);
        }
    }

    private void updateDados(ItensOrcamento itensOrcamentoBanco, ItensOrcamento novoItensOrcamento){
        itensOrcamentoBanco.setDescricao(novoItensOrcamento.getDescricao());
        itensOrcamentoBanco.setValorUnitario(novoItensOrcamento.getValorUnitario());
        itensOrcamentoBanco.setValorTotal(novoItensOrcamento.getValorTotal());

    }
}
