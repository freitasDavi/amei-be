package com.dggl.amei.repositories;

import com.dggl.amei.models.Orcamento;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>, CustomQueryDslPredicateExecutor<Orcamento> {

    @Query ("SELECT e FROM Orcamento e WHERE e.dataEmissaoOrcamento >= :dataInicio AND e.dataEmissaoOrcamento <= :dataFim")
    List<Orcamento> findByDataBetween(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);




}
