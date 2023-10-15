package com.dggl.amei.services;

import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class OrcamentoService {

    private static final Logger log = LoggerFactory.getLogger(OrcamentoService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Autowired
    private OrcamentoRepository repository;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    private String taskName = "Orçamento";

    public List<Orcamento> findAll(){
        return repository.findAll();
    }

    public Orcamento findById(Long id){
        Optional<Orcamento> orcamento = repository.findById(id);
        return orcamento.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }



//  Precisa configurar Cron expression
    @Scheduled(fixedRate = 10000)
    public void excluiOrcamentoMaiorTresMeses(){

//        log.info("Hora vindo do Orçamento Service {}", dateFormat.format(new Date()));

        int tempoMaximoExpurgoOrcamento = 90;

        for(Orcamento orcamento : findAll()){
            if(orcamento.getDataEmissaoOrcamento().until(Instant.now(), ChronoUnit.DAYS) > tempoMaximoExpurgoOrcamento){

//                delete(orcamento.getId());
            }
        }
    }

    public Orcamento insert(Orcamento orcamento){
        return repository.save(orcamento);
    }

    public void delete(Long id){
        try{
            if(!verificaExisteOrdemServicoNoOrcamento(id)){
                repository.deleteById(id);
            }
        }catch (EmptyResultDataAccessException e){
            throw new RecursoNaoEncontrado(taskName, id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    private boolean verificaExisteOrdemServicoNoOrcamento(Long id) {
        Orcamento orcamento = findById(id);
        if(orcamento.getOrcamentoOrdemServico() != null){
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
