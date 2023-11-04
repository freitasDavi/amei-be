package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.NovoOrcamentoRequest;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.repositories.ItensOrcamentoRepository;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
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

    @Autowired
    ItensOrcamentoRepository itensOrcamentoRepository;

    private String taskName = "Orçamento";

    public Page<Orcamento> findAll(String filter, Pageable pageable){
        return repository.findAll(filter, Orcamento.class, pageable);
    }

    public List<Orcamento> recuperarParaExpurgo () {
        LocalDateTime dias = LocalDateTime.now().plus(90, ChronoUnit.DAYS);

        return repository.findAllByDataEmissaoOrcamentoGreaterThan(dias);
    }

    public Orcamento findById(Long id){
        Optional<Orcamento> orcamento = repository.findById(id);
        return orcamento.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }

    public Orcamento insert(NovoOrcamentoRequest dto){
        var orcamento = new Orcamento(
                dto.getNomeCliente(),
                dto.getTelefoneCliente(),
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
                orc,
                item.getQuantidade())));

        if (!listaDeItensOrcamento.isEmpty())
            itensOrcamentoRepository.saveAll(listaDeItensOrcamento);

        return orc;
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
        orcamentoBanco.setTelefoneCliente(orcamento.getTelefoneCliente());
        orcamentoBanco.setValorTotalDoOrcamento(orcamento.getValorTotalDoOrcamento());


    }
}
