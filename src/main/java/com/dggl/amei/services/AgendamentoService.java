package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.AgendamentoRequestDTO;
import com.dggl.amei.dtos.responses.AgendamentoResponseDTO;
import com.dggl.amei.dtos.responses.relatorios.AgendamentoPorClienteDTO;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.models.QAgendamento;
import com.dggl.amei.models.User;
import com.dggl.amei.repositories.AgendamentoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;
    private String task = "Agendamento";

    public List<Agendamento> getLatestFive (Long id) {
        return repository.getAgendamentosByUsuarioAgendamento_IdOrderByDataAgendamentoAsc(id);
    }

    public Page<Agendamento> findAll(
            String filter, Pageable pageable, Long codigoUsuario
    ){
        return repository.findAll(filter, Agendamento.class, pageable, QAgendamento.agendamento.usuarioAgendamento.id.eq(codigoUsuario));
    }

    public Agendamento findById(Long id){
        Optional<Agendamento> obj =  repository.findById(id);
        return obj.orElseThrow(()-> new RecursoNaoEncontrado(task, id));
    }

    public void insert(AgendamentoRequestDTO obj){
        var novoAgendamento = obj.toEntity();

        repository.save(novoAgendamento);
    }

    public List<Agendamento> buscarRegistroEntreDatas(LocalDateTime dataInicio, LocalDateTime dataFim){
        return repository.findByDataBetween(dataInicio, dataFim);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new RecursoNaoEncontrado(task, id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Agendamento update(Long id, AgendamentoRequestDTO obj){
        try {
            Optional<Agendamento> entity = repository.findById(id);

            if (entity.isEmpty()) throw new RecursoNaoEncontrado(task, id);

            var entidade = entity.get();

            obj.toEntity(entidade);

            repository.save(entidade);

            return entidade;

        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(task, id);
        }
    }

    private void updateDados(Agendamento entity, Agendamento obj){
        entity.setDataAgendamento(obj.getDataAgendamento());
        entity.setAgendamentoBairro(obj.getAgendamentoBairro());
        entity.setAgendamentoCidade(obj.getAgendamentoCidade());
        entity.setClienteAgendamento(obj.getClienteAgendamento());
        entity.setEnderecoAgendamento(obj.getEnderecoAgendamento());
        entity.setResponsavelAgendamento(obj.getResponsavelAgendamento());
        entity.setTelefoneAgendamento(obj.getTelefoneAgendamento());
        entity.setTelefoneSecundario(obj.getTelefoneSecundario());

    }


//    --
    public List<AgendamentoPorClienteDTO> emitirRelatorio (Long codigoUsuario) {
        return repository.exportRelatorioAgendamentosAgrupadoPorCliente(new User(codigoUsuario));
    }

    public void exportaAgendamentoParaCsvPorPeriodo(Writer writer, LocalDateTime dataInicio, LocalDateTime dataFim){

        List<Agendamento> agendamentos = repository.findByDataBetween(dataInicio, dataFim);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("Cliente", "Cidade" , "Bairro", "Endereço", "Responsavel", "Data");
            for(Agendamento agendamento : agendamentos){
                csvPrinter.printRecord(
                        agendamento.getClienteAgendamento(),
                        agendamento.getAgendamentoCidade(),
                        agendamento.getAgendamentoBairro(),
                        agendamento.getEnderecoAgendamento(),
                        agendamento.getResponsavelAgendamento(),
                        agendamento.getDataAgendamento()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportaAgendamentoParaCsv(Writer writer){

        List<Agendamento> agendamentos = repository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)){
            csvPrinter.printRecord("Cliente", "Cidade" , "Bairro", "Endereço", "Responsavel", "Data");
            for(Agendamento agendamento : agendamentos){
                csvPrinter.printRecord(
                        agendamento.getClienteAgendamento(),
                        agendamento.getAgendamentoCidade(),
                        agendamento.getAgendamentoBairro(),
                        agendamento.getEnderecoAgendamento(),
                        agendamento.getResponsavelAgendamento(),
                        agendamento.getDataAgendamento()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
