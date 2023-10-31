package com.dggl.amei.repositories;

import com.dggl.amei.models.Cronometro;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CronometroRepository extends JpaRepository<Cronometro, Long>, CustomQueryDslPredicateExecutor<Cronometro> {

    Cronometro getCronometroByUsuario_Id(Long id);

    List<Cronometro> getCronometrosByUsuario_Id(Long id);
    Cronometro getCronometroByUsuario_IdAndAndCompletoEquals(Long id, boolean completo);
}
