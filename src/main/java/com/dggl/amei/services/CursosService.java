package com.dggl.amei.services;

import com.dggl.amei.dtos.responses.CursoResponseDTO;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
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

    public void update(Long id, Curso curso) {
        Optional<Curso> cursoOptional = repository.findById(id);

        if (cursoOptional.isEmpty()) {
            throw new RecursoNaoEncontrado(taskName, id);
        }

        Curso cursoAtualizado = cursoOptional.get();
        cursoAtualizado.setNomeCurso(curso.getNomeCurso());
        cursoAtualizado.setDescricao(curso.getDescricao());
        cursoAtualizado.setUrlCurso(curso.getUrlCurso());
        cursoAtualizado.setDataCurso(curso.getDataCurso());
        cursoAtualizado.setCidade(curso.getCidade());

        repository.save(cursoAtualizado);
    }
}
