package com.dggl.amei.services;

import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.Cronometro;
import com.dggl.amei.repositories.CronometroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CronometroService {

    @Autowired
    private CronometroRepository repository;

    @Autowired
    private OrdemServicoService ordemService;
    public List<Cronometro> getAllByUserId (Long id) {
        return repository.getCronometrosByUsuario_Id(id);
    }

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
        cronometro.setFim(LocalDateTime.now());
        repository.save(cronometro);
    }

    public Page<Cronometro> findAll(String filter, Pageable pageable) {
        return repository.findAll(filter, Cronometro.class, pageable);
    }

    public Long gerarNovaOrdem (Long codigoCronometro) {
        var relogio = repository.findById(codigoCronometro);

        if (relogio.isEmpty()) {
            throw new RecursoNaoEncontrado("Cronomêtro", codigoCronometro);
        }

        var valorTrabalhado = getValorServico(relogio.get().getValorHora(), relogio.get().getInicio(), relogio.get().getFim());

        var codigoOrdem = ordemService.gerarOrdemDoRelogio(relogio.get(), valorTrabalhado);

        return codigoOrdem;
    }

    public BigDecimal getValorServico (BigDecimal valor, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        long diferencaEmHoras = ChronoUnit.HOURS.between(dataInicial, dataFinal);

        if (diferencaEmHoras <= 0)
            diferencaEmHoras = 1L;

        return valor.multiply(BigDecimal.valueOf(diferencaEmHoras));
    }
}
