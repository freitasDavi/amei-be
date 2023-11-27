package com.dggl.amei;

import com.dggl.amei.models.Agendamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.repositories.OrcamentoRepository;
import com.dggl.amei.services.AgendamentoService;
import com.dggl.amei.services.OrcamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private AgendamentoService agendamentoService;

    @Scheduled(cron = "0 0 8 * * *")
    public void excluiOrcamentoMaiorTresMeses(){
        var orcamentos = orcamentoService.recuperarParaExpurgo();

        if (orcamentos == null) return;

        log.info("Recuperamos orçamentos para excluir");
        for (Orcamento orcamento: orcamentos) {
            orcamentoService.delete(orcamento.getId());
            log.info("Orçamento {} excluido", orcamento.getId());
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void excluiAgendamentoMaiorTresMeses(){
        var agendamentos = agendamentoService.recuperarAgendamentoParaExpurgo();

        if(agendamentos == null) return;

        log.info("Recuperamos agendamentos para exlcuir");
        for(Agendamento agendamento : agendamentos){
            agendamentoService.delete(agendamento.getId());
            log.info("Agendamento {} excluido", agendamento.getId());
        }
    }
}
