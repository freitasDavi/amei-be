package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.NovoServicoRequest;
import com.dggl.amei.dtos.requests.UpdateServicoRequest;
import com.dggl.amei.exceptions.DataBaseException;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Servico;
import com.dggl.amei.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;
    private String task = "Serviço";

    public Page<Servico> findAll(String filter, Pageable pageable){
        return repository.findAll(filter, Servico.class, pageable);
    }

    public Servico findById(Long id){
        Optional<Servico> listaServico =  repository.findById(id);
        return listaServico.orElseThrow(()-> new RecursoNaoEncontrado(task, id));
    }

    public Servico insert(NovoServicoRequest dto){
        var novoServico = new Servico(dto.getDescricaoServico(), dto.getValorServico(), dto.getCodigoCNAE(), dto.getServicoUsuario());

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

    public void update(Long id, UpdateServicoRequest update){
        var servicoBanco = repository.findById(id);

        if (servicoBanco.isPresent()) {
            var entidade = servicoBanco.get();
            updateDados(entidade, update);

            repository.save(entidade);
            return;
        }

        throw new RecursoNaoEncontrado(task, id);
    }

    private void updateDados(Servico servicoBanco, UpdateServicoRequest request){
        servicoBanco.setCodigoCNAE(request.getCodigoCNAE());
        servicoBanco.setDescricaoServico(request.getDescricaoServico());
        servicoBanco.setValorServico(request.getValorServico());
    }

}
