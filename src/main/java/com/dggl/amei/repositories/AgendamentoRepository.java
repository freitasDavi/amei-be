package com.dggl.amei.repositories;

import com.dggl.amei.dtos.responses.relatorios.AgendamentoPorClienteDTO;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.models.User;
import com.dggl.amei.utils.CustomQueryDslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, CustomQueryDslPredicateExecutor<Agendamento> {

    List<Agendamento> getAgendamentosByUsuarioAgendamento_IdOrderByDataAgendamentoAsc(Long userId);

    @Query("SELECT e FROM Agendamento e WHERE e.dataAgendamento >= :dataInicio AND e.dataAgendamento <= :dataFim")
    List<Agendamento> findByDataBetween(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT NEW com.dggl.amei.dtos.responses.relatorios.AgendamentoPorClienteDTO(cl.nomeCliente, cl.cnpjCliente, cl.telefoneCliente, COUNT(ag.id)) " +
            "FROM Agendamento ag  INNER JOIN Clientes cl ON ag.clienteAgendamento = cl WHERE ag.usuarioAgendamento = :codigoUsuario GROUP BY cl.nomeCliente, cl.id")
    List<AgendamentoPorClienteDTO> exportRelatorioAgendamentosAgrupadoPorCliente(User codigoUsuario);

    @Query("SELECT NEW com.dggl.amei.dtos.responses.relatorios.AgendamentoPorClienteDTO(cl.nomeCliente, cl.cnpjCliente, cl.telefoneCliente, COUNT(ag.id)) " +
            "FROM Agendamento ag  INNER JOIN Clientes cl ON ag.clienteAgendamento = cl " +
            "WHERE ag.usuarioAgendamento = :codigoUsuario " +
            "AND ag.dataAgendamento >= :dataInicio AND ag.dataAgendamento <= :dataFim GROUP BY cl.nomeCliente, cl.id")
    List<AgendamentoPorClienteDTO> exportRelatorioAgendamentosAgrupadoPorCliente(User codigoUsuario, LocalDate dataInicio, LocalDate dataFim);
}
