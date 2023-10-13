package com.example.schedulingtasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TarefasAgendamento {

    private static final Logger log = LoggerFactory.getLogger(TarefasAgendamento.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    Saber configurar essa expresão aqui
    @Scheduled(cron = "30 9 15 * ?")
    public void informaTempoAtual(){
        log.info("A hora agora é: {}", dateFormat.format(new Date()));
    }
}
