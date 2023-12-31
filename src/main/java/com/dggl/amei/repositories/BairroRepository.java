package com.dggl.amei.repositories;

import com.dggl.amei.models.Bairro;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>, CustomQueryDslPredicateExecutor<Bairro> {

    Bairro findFirstByNomeBairroContaining(String nomeBairro);
}
