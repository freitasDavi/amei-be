package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.ItemOrcamentoRequestDTO;
import com.dggl.amei.dtos.requests.NovoOrcamentoRequest;
import com.dggl.amei.dtos.requests.UpdateOrcamentoRequest;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.models.QOrcamento;
import com.dggl.amei.models.enums.StatusOrcamentoEnum;
import com.dggl.amei.repositories.ItensOrcamentoRepository;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.aspectj.weaver.ast.Or;
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
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public Page<Orcamento> findAll(String filter, Pageable pageable, Long id) {
        return repository.findAll(filter, Orcamento.class, pageable, QOrcamento.orcamento.usuarioOrcamento.id.eq(id));
    }

    public List<Orcamento> recuperarParaExpurgo() {
        LocalDateTime dias = LocalDateTime.now().plus(90, ChronoUnit.DAYS);

        return repository.findAll(QOrcamento.orcamento.dataEmissaoOrcamento.after(dias).and(QOrcamento.orcamento.status.eq(StatusOrcamentoEnum.ABERTO)));
    }

    public Orcamento findById(Long id) {
        Optional<Orcamento> orcamento = repository.findById(id);
        return orcamento.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }
    public List<Orcamento> buscaOrcamentoPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {

        List<Orcamento> orcamentos = repository.findAll();
        List<Orcamento> orcamentosFiltrados = new LinkedList<>();
        for (Orcamento orcamento : orcamentos) {
            if ((orcamento.getDataEmissaoOrcamento().isAfter(dataInicio)) && orcamento.getDataEmissaoOrcamento().isBefore(dataFim)) {
                orcamentosFiltrados.add(orcamento);
            }
        }

        return orcamentosFiltrados;
    }

    public Orcamento insert(NovoOrcamentoRequest dto) {
        var listItems = ItemOrcamentoRequestDTO.toEntity(dto.getItensOrcamentos());

        var orcamento = new Orcamento(
                dto.getNomeCliente(),
                dto.getTelefoneCliente(),
                dto.getDataValidadeOrcamento(),
                dto.getValorTotalDoOrcamento(),
                dto.getObservacoesOrcamento(),
                dto.getUsuarioOrcamento(),
                dto.getClienteOrcamento(),
                listItems
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

    public void delete(Long id) {
        try {
            if (!verificaExisteOrdemServicoNoOrcamento(id)) {
                var items = itensOrcamentoRepository.findAllByOrcamento(id);

                itensOrcamentoRepository.deleteAll(items);
                repository.deleteById(id);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNaoEncontrado(taskName, id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private boolean verificaExisteOrdemServicoNoOrcamento(Long id) {
        Orcamento orcamento = findById(id);
        if (orcamento.getOrcamentoOrdemServico() != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public void update(Long id, UpdateOrcamentoRequest dto) {
        try {
            if (dto.getOrcamentoOrdemServico() != null) {
//                Tratar como enviar o erro para o front
            } else {
                Optional<Orcamento> value = repository.findById(id);

                if (value.isEmpty()) {
                    throw new RecursoNaoEncontrado(taskName, id);
                }

                var entidade = value.get();

                updateDados(entidade, dto);

                repository.save(entidade);

                var listItems = ItemOrcamentoRequestDTO.toEntity(dto.getItensOrcamentos());

                updateItemsOrcamento(id, listItems);


            }
        } catch (EntityNotFoundException e) {
            throw new RecursoNaoEncontrado(taskName, id);
        }
    }

    public void updateDados(Orcamento entidade, UpdateOrcamentoRequest dto) {
        entidade.setClienteOrcamento(dto.getClienteOrcamento());
        entidade.setDataValidadeOrcamento(dto.getDataValidadeOrcamento());
        entidade.setObservacoesOrcamento(dto.getObservacoesOrcamento());
        entidade.setTelefoneCliente(dto.getTelefoneCliente());
        entidade.setValorTotalDoOrcamento(dto.getValorTotalDoOrcamento());
        entidade.setNomeCliente(dto.getNomeCliente());
    }

    public void updateItemsOrcamento(Long codigoOrcamento, List<ItensOrcamento> items) {
        List<ItensOrcamento> listaNovos = new LinkedList<>();

        items.forEach(item -> {
            if (item.getId() == null) {
                listaNovos.add(new ItensOrcamento(
                        item.getValorUnitario(),
                        item.getValorTotal(),
                        item.getDescricao(),
                        new Orcamento(codigoOrcamento),
                        item.getQuantidade()));
            } else {
                updateItemOrcamento(item);
            }
        });

        itensOrcamentoRepository.saveAll(listaNovos);
    }

    public void updateItemOrcamento(ItensOrcamento item) {
        Optional<ItensOrcamento> value = itensOrcamentoRepository.findById(item.getId());

        if (value.isEmpty()) {
            throw new RecursoNaoEncontrado(taskName, item.getId());
        }

        var entidade = value.get();

        entidade.setDescricao(item.getDescricao());
        entidade.setQuantidade(item.getQuantidade());
        entidade.setValorTotal(item.getValorTotal());
        entidade.setValorUnitario(item.getValorUnitario());

        itensOrcamentoRepository.save(entidade);
    }

    public void exportaOrcamentoParaCsvPorPeriodo(Writer writer, LocalDateTime dataInicio, LocalDateTime dataFim) {

        List<Orcamento> orcamentos = repository.findByDataBetween(dataInicio, dataFim);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Cliente", "Data Emissão", "Valor Total");
            for (Orcamento orcamento : orcamentos) {
                csvPrinter.printRecord(
                        orcamento.getNomeCliente(),
                        orcamento.getDataEmissaoOrcamento(),
                        orcamento.getValorTotalDoOrcamento()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportaOrcamentoParaCsv(Writer writer) {

        List<Orcamento> orcamentos = repository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Cliente", "Data Emissão", "Valor Total");
            for (Orcamento orcamento : orcamentos) {
                csvPrinter.printRecord(
                        orcamento.getNomeCliente(),
                        orcamento.getDataEmissaoOrcamento(),
                        orcamento.getValorTotalDoOrcamento()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
