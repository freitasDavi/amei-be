package com.dggl.amei.repositories;

import com.dggl.amei.models.Clientes;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Clientes, Long>, CustomQueryDslPredicateExecutor<Clientes> {
}
