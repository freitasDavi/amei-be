package com.dggl.amei.repositories;

import com.dggl.amei.models.Orcamento;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>, CustomQueryDslPredicateExecutor<Orcamento> {

    List<Orcamento> findAllByDataEmissaoOrcamentoGreaterThan(LocalDateTime test);
}
