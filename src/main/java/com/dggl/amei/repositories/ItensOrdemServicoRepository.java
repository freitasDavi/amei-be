package com.dggl.amei.repositories;

import com.dggl.amei.models.ItensOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensOrdemServicoRepository extends JpaRepository<ItensOrdemServico, Long> {
}
