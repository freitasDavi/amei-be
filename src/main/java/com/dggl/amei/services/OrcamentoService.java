package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.NovoOrcamentoRequest;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.repositories.ItensOrcamentoRepository;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    @Autowired
    ItensOrcamentoRepository itensOrcamentoRepository;

    private String taskName = "Orçamento";

    public List<Orcamento> findAll(){
        return repository.findAll();
    }

    public Orcamento findById(Long id){
        Optional<Orcamento> orcamento = repository.findById(id);
        return orcamento.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }

    public Orcamento insert(NovoOrcamentoRequest dto){
        var orcamento = new Orcamento(
                dto.getTelefoneClienteOrcamento(),
                dto.getDataValidadeOrcamento(),
                dto.getValorTotalDoOrcamento(),
                dto.getObservacoesOrcamento(),
                dto.getUsuarioOrcamento(),
                dto.getClienteOrcamento(),
                dto.getItensOrcamentos()
        );

        var orc = repository.save(orcamento);

        List<ItensOrcamento> listaDeItensOrcamento = new LinkedList<>();

        dto.getItensOrcamentos().forEach(item -> listaDeItensOrcamento.add(new ItensOrcamento(
                item.getValorUnitario(),
                item.getValorTotal(),
                item.getDescricao(),
                orc)));

        if (!listaDeItensOrcamento.isEmpty())
            itensOrcamentoRepository.saveAll(listaDeItensOrcamento);

        return orc;
    }

    public void delete(Long id){
        try{
            if(verificaOrdemServico()){
                repository.deleteById(id);
            }
        }catch (EmptyResultDataAccessException e){
            throw new RecursoNaoEncontrado(taskName, id);
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

    public Orcamento update(Long id, Orcamento orcamento){
        try{
            if(orcamento.getOrcamentoOrdemServico() != null){
//                Tratar como enviar o erro para o front
            }else {
                Orcamento orcamentoBanco = repository.getReferenceById(id);
                updateDados(orcamentoBanco, orcamento);
                return repository.save(orcamentoBanco);
            }
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(taskName, id);
        }
        return null;
    }

    private void updateDados(Orcamento orcamentoBanco, Orcamento orcamento){
//        Ver questão de como editar os itens do orçamento
        orcamentoBanco.setClienteOrcamento(orcamento.getClienteOrcamento());
        orcamentoBanco.setDataValidadeOrcamento(orcamento.getDataValidadeOrcamento());
        orcamentoBanco.setObservacoesOrcamento(orcamento.getObservacoesOrcamento());
        orcamentoBanco.setTelefoneClienteOrcamento(orcamento.getTelefoneClienteOrcamento());
        orcamentoBanco.setValorTotalDoOrcamento(orcamento.getValorTotalDoOrcamento());


    }
}
