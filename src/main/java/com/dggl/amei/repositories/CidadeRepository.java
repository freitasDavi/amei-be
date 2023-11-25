package com.dggl.amei.repositories;

import com.dggl.amei.models.Cidade;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, CustomQueryDslPredicateExecutor<Cidade> {

    Cidade findByNomeCidadeContaining(String nomeCidade);
}
