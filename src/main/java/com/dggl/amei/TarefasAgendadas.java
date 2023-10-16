package com.dggl.amei;

import com.dggl.amei.models.Orcamento;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.services.OrcamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TarefasAgendadas {

    private static final Logger log = LoggerFactory.getLogger(OrcamentoService.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private OrcamentoService orcamentoService;

    private OrcamentoRepository orcamentoRepository;
    @Scheduled(fixedRate = 5000)
    public void excluiOrcamentoMaiorTresMeses(){

        log.info("The time is now {}", dateFormat.format(new Date()));

        int tempoMaximoExpurgoOrcamento = 90;


        for(Orcamento orcamento : orcamentoService.findAll()){
            if(orcamento.getDataEmissaoOrcamento().until(Instant.now(), ChronoUnit.DAYS) > tempoMaximoExpurgoOrcamento){
                orcamentoService.delete(orcamento.getId());
                log.info("Orcamento apagado {}", orcamento.getId());
            }
        }
    }
}
