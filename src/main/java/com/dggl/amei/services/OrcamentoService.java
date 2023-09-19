package com.dggl.amei.services;

import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public List<Orcamento> findAll(){
        return repository.findAll();
    }

    public Orcamento findById(Long id){
        Optional<Orcamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontrado(id));
    }

    public Orcamento insert(Orcamento obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try{
            if(verificaOrdemServico()){
                repository.deleteById(id);
            }
        }catch (EmptyResultDataAccessException e){
            throw new RecursoNaoEncontrado(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public boolean verificaOrdemServico() {
        Orcamento obj = new Orcamento();
        if(obj.getOrcamentoOrdemServico() == null){
            return true;
        }
        return false;
    }

    public Orcamento update(Long id, Orcamento obj){
        try{
            if(obj.getOrcamentoOrdemServico() != null){
//                Tratar como enviar o erro para o front
            }else {
                Orcamento entity = repository.getReferenceById(id);
                updateDados(entity, obj);
                return repository.save(entity);
            }
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(id);
        }
        return null;
    }

    private void updateDados(Orcamento entity, Orcamento obj){
//        Ver questão de como editar os itens do orçamento
        entity.setClienteOrcamento(obj.getClienteOrcamento());
        entity.setDataValidadeOrcamento(obj.getDataValidadeOrcamento());
        entity.setObservacoesOrcamento(obj.getObservacoesOrcamento());
        entity.setTelefoneClienteOrcamento(obj.getTelefoneClienteOrcamento());
        entity.setValorTotalDoOrcamento(obj.getValorTotalDoOrcamento());


    }
}
