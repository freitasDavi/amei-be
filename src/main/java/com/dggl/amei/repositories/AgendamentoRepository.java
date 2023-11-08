package com.dggl.amei.repositories;

import com.dggl.amei.models.Agendamento;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, CustomQueryDslPredicateExecutor<Agendamento> {

    List<Agendamento> getAgendamentosByUsuarioAgendamento_IdOrderByDataAgendamentoDesc(Long userId);
}
