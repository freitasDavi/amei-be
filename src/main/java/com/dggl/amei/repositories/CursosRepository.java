package com.dggl.amei.repositories;

import com.dggl.amei.models.Curso;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Curso, Long>, CustomQueryDslPredicateExecutor<Curso> {
}
