package com.dggl.amei.services;

import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Cronometro;
import com.dggl.amei.repositories.CronometroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CronometroService {

    @Autowired
    private CronometroRepository repository;

    public Cronometro getUltimoCronometroAtivo(Long id) {
        return repository.getCronometroByUsuario_IdAndAndCompletoEquals(id, false);
    }

    public void create(Cronometro cronometro) {
        repository.save(cronometro);
    }

    public void stopCronometro(Long id) {
        var entidadeOpt = repository.findById(id);

        if (entidadeOpt.isEmpty()) throw new RecursoNaoEncontrado("Cronômetro", id);

        var cronometro = entidadeOpt.get();

        cronometro.setCompleto(true);
        cronometro.setFim(Instant.now());
        repository.save(cronometro);
    }

    public Page<Cronometro> findAll(String filter, Pageable pageable) {
        return repository.findAll(filter, Cronometro.class, pageable);
    }
}
