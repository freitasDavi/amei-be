package com.dggl.amei.repositories;

import com.dggl.amei.models.ItensOrcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensOrcamentoRepository extends JpaRepository<ItensOrcamento, Long> {
}
