package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.NovoServicoRequest;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Servico;
import com.dggl.amei.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;
    private String task = "Serviço";

    public List<Servico> findAll(){
        return repository.findAll();
    }

    public Servico findById(Long id){
        Optional<Servico> listaServico =  repository.findById(id);
        return listaServico.orElseThrow(()-> new RecursoNaoEncontrado(task, id));
    }

    public Servico insert(NovoServicoRequest dto){
        var novoServico = new Servico(dto.getDescricao(), dto.getValor(), dto.getCodigoCNAE());

        repository.save(novoServico);

        return novoServico;
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

    public Servico update(Long id, Servico update){
        try {
            Servico servicoBanco = repository.getReferenceById(id);

            updateDados(servicoBanco, update);

            return repository.save(servicoBanco);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontrado(task, id);
        }
    }

    private void updateDados(Servico servicoBanco, Servico novoServico){
        servicoBanco.setDescricaoServico(novoServico.getDescricaoServico());
        servicoBanco.setValorServico(novoServico.getValorServico());
    }

}
