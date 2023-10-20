package com.dggl.amei.repositories;

import com.dggl.amei.models.Agendamento;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, CustomQueryDslPredicateExecutor<Agendamento> {
}
