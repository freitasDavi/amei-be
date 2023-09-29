package com.dggl.amei.services;

import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public List<Agendamento> findAll(){
        return repository.findAll();
    }

    public Agendamento findById(Long id){
        Optional<Agendamento> obj =  repository.findById(id);
        return obj.orElseThrow(()-> new RecursoNaoEncontrado(id));
    }

    public Agendamento insert(Agendamento obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new RecursoNaoEncontrado(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Agendamento update(Long id, Agendamento obj){
        try {
            Agendamento entity = repository.getReferenceById(id);
            updateDados(entity, obj);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(id);
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

}
