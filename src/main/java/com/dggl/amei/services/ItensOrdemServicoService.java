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
    private String taskName = "Item da Ordem";

    public List<ItensOrdemServico> findAll() {
        return repository.findAll();
    }

    public void saveAll(List<ItensOrdemServico> listaItensOrdemServico){
        repository.saveAll(listaItensOrdemServico);
    }

    public ItensOrdemServico findById(Long id) {
        Optional<ItensOrdemServico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }

    public ItensOrdemServico insert(ItensOrdemServico itensOrdemServico) {
        return repository.save(itensOrdemServico);
    }

    public void delete(Long id) {
        try {
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontrado(taskName, id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }


    public ItensOrdemServico update(Long id, ItensOrdemServico itensOrdemServico) {
        try {
            ItensOrdemServico itensOrdemServicoBanco = repository.getReferenceById(id);
            updateDados(itensOrdemServicoBanco, itensOrdemServico);
            return repository.save(itensOrdemServicoBanco);
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado(taskName, id);
        }
    }
    
    private void updateDados(ItensOrdemServico itensOrdemServicoBanco, ItensOrdemServico novosItensOrdemServico){
        itensOrdemServicoBanco.setValorUnitario(novosItensOrdemServico.getValorUnitario());
        itensOrdemServicoBanco.setValorTotal(novosItensOrdemServico.getValorTotal());
        itensOrdemServicoBanco.setValorUnitario(novosItensOrdemServico.getValorUnitario());
        itensOrdemServicoBanco.setDescricaoItemOrdem(novosItensOrdemServico.getDescricaoItemOrdem());
        itensOrdemServicoBanco.setOrdemDeServico(novosItensOrdemServico.getOrdemDeServico());

    }
}
