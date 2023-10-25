package com.dggl.amei.repositories;


import com.dggl.amei.models.Estado;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>, CustomQueryDslPredicateExecutor<Estado> {
}
