package com.dggl.amei.repositories;

import com.dggl.amei.models.Pagamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentosRepository extends JpaRepository<Pagamentos, Long> {

    Pagamentos findFirstByUsuarioPagamento_IdOrderByDataPagamentoDesc(Long codigoUsuario);

}
