package com.dggl.amei.repositories;

import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>, CustomQueryDslPredicateExecutor<OrdemServico> {
}
