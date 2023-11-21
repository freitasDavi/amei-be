package com.dggl.amei.repositories;

import com.dggl.amei.models.Agendamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, CustomQueryDslPredicateExecutor<Agendamento> {

    List<Agendamento> getAgendamentosByUsuarioAgendamento_IdOrderByDataAgendamentoAsc(Long userId);

    @Query("SELECT e FROM Agendamento e WHERE e.dataAgendamento >= :dataInicio AND e.dataAgendamento <= :dataFim")
    List<Agendamento> findByDataBetween(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}
