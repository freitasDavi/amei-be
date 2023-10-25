package com.dggl.amei.repositories;

import com.dggl.amei.models.Servico;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>, CustomQueryDslPredicateExecutor<Servico> {
}
