package com.dggl.amei.services;

import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository repository;

    private String taskName = "Ordem de Serviço";

    public List<OrdemServico> findAll(){
        return repository.findAll();
    }

    public OrdemServico findById(Long id){
        Optional<OrdemServico> ordemServico = repository.findById(id);
        return ordemServico.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }

    public OrdemServico insert(OrdemServico ordemServico){
        return repository.save(ordemServico);
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
