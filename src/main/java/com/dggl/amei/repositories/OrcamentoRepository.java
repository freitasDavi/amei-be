package com.dggl.amei.repositories;

import com.dggl.amei.models.Orcamento;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>, CustomQueryDslPredicateExecutor<Orcamento> {
}
