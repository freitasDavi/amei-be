package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.NovoOrdemServicoRequest;
import com.dggl.amei.dtos.requests.UpdateOrdemServicoRequest;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.ItensOrdemServico;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.models.enums.StatusOrdemServicoEnum;
import com.dggl.amei.repositories.ItensOrdemServicoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Writer;
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
    private ItensOrdemServicoRepository itensOrdemServicoRepository;

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
                dto.getClienteOrdem(),
                dto.getTelefoneOrdem(),
                dto.getValorTotal(),
                dto.getUsuarioOrdem(),
                dto.getDataEmissaoOrdemServico()
        );

        var ordem = repository.save(ordemServico);

        List<ItensOrdemServico> listaDeItensDaOrdemServido = new LinkedList<>();

        dto.getItensOrdemServicos().forEach(item -> listaDeItensDaOrdemServido.add(new ItensOrdemServico(
                item.getValorUnitario(),
                item.getValorTotal(),
                item.getDescricaoItemOrdem(),
                item.getOrdemDeServico()
                )));

        if (!listaDeItensDaOrdemServido.isEmpty())
            itensOrdemServicoRepository.saveAll(listaDeItensDaOrdemServido);

        return ordem;
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
                item.getValorUnitario(),
                item.getValorTotal(),
                item.getDescricao(),
                new OrdemServico(codigoOrdem))));
        itensOrdemServicoRepository.saveAll(listaItensOrdemServico);

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

    @Transactional
    public void update(Long id, UpdateOrdemServicoRequest dto){
        try{
            if(dto.getOrdemDeServicoOrcamento() != null){
//                Tratar como enviar o erro para o front
            }else {
                Optional<OrdemServico> value = repository.findById(id);

                if (value.isEmpty()) {
                    throw new RecursoNaoEncontrado(taskName, id);
                }

                var entidade = value.get();

                updateDados(entidade, dto);

                repository.save(entidade);

                updateItemsOrdemDeServico(id, dto.getItensOrdemServicos());

            }
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(taskName, id);
        }
    }

    private void updateDados(OrdemServico entidade, UpdateOrdemServicoRequest dto){
        entidade.setClienteOrdem(dto.getClienteOrdem());
        entidade.setDataEmissaoOrdemServico(dto.getDataEmissaoOrdemServico());
        entidade.setValorTotal(dto.getValorTotal());
        entidade.setTelefoneOrdem(dto.getTelefoneOrdem());
        entidade.setClienteOrdem(dto.getClienteOrdem());
    }

    private void updateItemsOrdemDeServico(Long codigoOrdemServico, List<ItensOrdemServico> items) {
        List<ItensOrdemServico> listaNovos = new LinkedList<>();

        items.forEach(item -> {
            if (item.getOrdemDeServico() == null) {
                listaNovos.add(new ItensOrdemServico(
                        item.getValorUnitario(),
                        item.getValorTotal(),
                        item.getDescricaoItemOrdem(),
                        new OrdemServico(codigoOrdemServico)));
            } else {
                updateItemsOrdemDeServico(item);
            }
        });

        itensOrdemServicoRepository.saveAll(listaNovos);
    }

    private void updateItemsOrdemDeServico(ItensOrdemServico item) {
        Optional<ItensOrdemServico> value = itensOrdemServicoRepository.findById(item.getId());

        if (value.isEmpty()) {
            throw new RecursoNaoEncontrado(taskName, item.getId());
        }

        var entidade = value.get();

        entidade.setDescricaoItemOrdem(item.getDescricaoItemOrdem());
        entidade.setValorUnitario(item.getValorUnitario());
        entidade.setValorTotal(item.getValorTotal());

        itensOrdemServicoRepository.save(entidade);
    }


    public void exportaOrdemDeServicoParaCsvPorPeriodo(Writer writer, LocalDateTime dataInicio, LocalDateTime dataFim){

        List<OrdemServico> ordens = repository.findByDataBetween(dataInicio, dataFim);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("Cliente", "Status da Ordem", "Data de Emissão", "Valor Total");
            for(OrdemServico ordemDeServico : ordens){
                csvPrinter.printRecord(
                        ordemDeServico.getClienteOrdem(),
                        ordemDeServico.getStatusOrdemServico(),
                        ordemDeServico.getDataEmissaoOrdemServico(),
                        ordemDeServico.getValorTotal()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportaOrdemDeServicoParaCsv(Writer writer){

        List<OrdemServico> ordens = repository.findAll();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("Cliente", "Status da Ordem", "Data de Emissão", "Valor Total");
            for(OrdemServico ordemDeServico : ordens){
                csvPrinter.printRecord(
                        ordemDeServico.getClienteOrdem(),
                        ordemDeServico.getStatusOrdemServico(),
                        ordemDeServico.getDataEmissaoOrdemServico(),
                        ordemDeServico.getValorTotal()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
