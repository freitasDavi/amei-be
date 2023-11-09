package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.NovoOrcamentoRequest;
import com.dggl.amei.dtos.requests.NovoOrdemServicoRequest;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.ItensOrdemServico;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.models.enums.StatusOrdemServicoEnum;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private ItensOrdemServicoService itensOrdemServicoService;

    private String taskName = "Ordem de Serviço";

    public Page<OrdemServico> findAll(String filter, Pageable pageable){
        return repository.findAll(filter, OrdemServico.class, pageable);
    }

    public OrdemServico findById(Long id){
        Optional<OrdemServico> ordemServico = repository.findById(id);
        return ordemServico.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }

    public OrdemServico insert(NovoOrdemServicoRequest dto){
        var ordemServico = new OrdemServico(
                dto.getUsuarioOrdem(),
                dto.getTelefoneOrdem(),
                dto.getValorTotal(),
                dto.getItensOrdemServicos(),
                dto.getClienteOrdem(),
                dto.getDataEmissaoOrdemServico()
        );


        var ordem = repository.save(ordemServico);

        List<ItensOrdemServico> listaDeItensDaOrdemServido = new LinkedList<>();

        dto.getItensOrdemServicos().forEach(item -> listaDeItensDaOrdemServido.add(new ItensOrdemServico(
                item.getValorTotal(),
                item.getValorUnitario(),
                item.getOrdemItens(),
                ordem,
                item.getDescricaoItemOrdem())));

        if (!listaDeItensOrcamento.isEmpty())
            itensOrcamentoRepository.saveAll(listaDeItensOrcamento);

        return orc;
    }

    public void geraOrdemDeServicoVindoDoOrcamento(Long id){
        var orcamento = orcamentoService.findById(id);

        var ordemServico = new OrdemServico();
        ordemServico.setClienteOrdem(ordemServico.getClienteOrdem());
        ordemServico.setStatusOrdemServico(StatusOrdemServicoEnum.AGUARDANDO_EMISSAO);
        ordemServico.setDataEmissaoOrdemServico(LocalDateTime.now());
        ordemServico.setValorTotal(ordemServico.getValorTotal());
        repository.save(ordemServico);
        geraListaDeItensOrdemServico(orcamento.getItensOrcamentos(), ordemServico.getId());

    }


    public void geraListaDeItensOrdemServico(List<ItensOrcamento> itensOrc, Long codigoOrdem){
        List<ItensOrdemServico> listaItensOrdemServico = new ArrayList<>();

        itensOrc.forEach(item -> listaItensOrdemServico.add(new ItensOrdemServico(
                item.getId(),
                item.getValorUnitario(),
                item.getValorTotal(),
                item.getDescricao(),
                new OrdemServico(codigoOrdem))));
        itensOrdemServicoService.saveAll(listaItensOrdemServico);

    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new RecursoNaoEncontrado(taskName, id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public OrdemServico update(Long id, OrdemServico ordemServico){
        try {
            OrdemServico ordemServicoBanco = repository.getReferenceById(id);
            updateDados(ordemServicoBanco, ordemServico);
            return repository.save(ordemServicoBanco);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(taskName, id);
        }
    }

    public void updateDados(OrdemServico ordemServicoBanco, OrdemServico ordemServico){
        ordemServicoBanco.setTelefoneOrdem(ordemServico.getTelefoneOrdem());
        ordemServicoBanco.setValorTotal(ordemServico.getValorTotal());
    }



}
