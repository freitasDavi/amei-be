package com.dggl.amei.repositories;

import com.dggl.amei.models.Orcamento;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, CustomQueryDslPredicateExecutor<OrdemServico> {

    @Query("SELECT e FROM OrdemServico e WHERE e.dataEmissaoOrdemServico >= :dataInicio AND e.dataEmissaoOrdemServico <= :dataFim")
    List<OrdemServico> findByDataBetween(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}
