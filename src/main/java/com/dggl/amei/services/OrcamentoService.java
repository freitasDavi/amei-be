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
        Optional<Orcamento> orcamento = repository.findById(id);
        return orcamento.orElseThrow(() -> new RecursoNaoEncontrado(id));
    }

    public Orcamento insert(Orcamento novoOrcamento){
        return repository.save(novoOrcamento);
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
        Orcamento orcamento = new Orcamento();
        if(orcamento.getOrcamentoOrdemServico() == null){
            return true;
        }
        return false;
    }

    public Orcamento update(Long id, Orcamento dadosEdicao){
        try{
            if(dadosEdicao.getOrcamentoOrdemServico() != null){
//                Tratar como enviar o erro para o front
            }else {
                Orcamento orcBanco = repository.getReferenceById(id);
                updateDados(orcBanco, dadosEdicao);
                return repository.save(orcBanco);
            }
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(id);
        }
        return null;
    }

    private void updateDados(Orcamento orcBanco, Orcamento orcUpdate){
//        Ver questão de como editar os itens do orçamento
        orcBanco.setClienteOrcamento(orcUpdate.getClienteOrcamento());
        orcBanco.setDataValidadeOrcamento(orcUpdate.getDataValidadeOrcamento());
        orcBanco.setObservacoesOrcamento(orcUpdate.getObservacoesOrcamento());
        orcBanco.setTelefoneClienteOrcamento(orcUpdate.getTelefoneClienteOrcamento());
        orcBanco.setValorTotalDoOrcamento(orcUpdate.getValorTotalDoOrcamento());


    }
}
