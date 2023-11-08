package com.dggl.amei.services;

import com.dggl.amei.dtos.responses.CursoResponseDTO;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.models.Curso;
import com.dggl.amei.repositories.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursosService {

    @Autowired
    private CursosRepository repository;
    private String taskName = "Curso";

    public Page<Curso> findAll(String filter, Pageable pageable) {
        return repository.findAll(filter, Curso.class, pageable);
    }

    public Curso findById(Long id) {
        Optional<Curso> curso = repository.findById(id);

        return curso.orElseThrow(() -> new RecursoNaoEncontrado(taskName, id));
    }

    public void insert(CursoResponseDTO curso) {
        var novoCurso = curso.toEntity();

        repository.save(novoCurso);
    }

    public void update(Long id, CursoResponseDTO dto) {
        Optional<Curso> cursoOptional = repository.findById(id);

        if (cursoOptional.isEmpty()) {
            throw new RecursoNaoEncontrado(taskName, id);
        }

        Curso entidade = cursoOptional.get();
        entidade.setNomeCurso(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
        entidade.setUrlCurso(dto.getUrl());
        entidade.setDataCurso(dto.getData());
        entidade.setCidade(new Cidade(dto.getCodigoCidade()));

        repository.save(entidade);
    }
}
