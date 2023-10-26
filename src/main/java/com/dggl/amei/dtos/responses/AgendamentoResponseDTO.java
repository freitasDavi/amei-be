package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.Agendamento;
import com.dggl.amei.models.Bairro;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.models.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class AgendamentoResponseDTO {

    private Long id;

    private LocalDate dataAgendamento;

    private String enderecoAgendamento;

    private String responsavelAgendamento;

    private String telefoneAgendamento;

    private String telefoneSecundario;

    private Clientes clienteAgendamento;

    private Cidade agendamentoCidade;

    private Bairro agendamentoBairro;

    public static AgendamentoResponseDTO fromEntity(Agendamento agendamento) {
        AgendamentoResponseDTO dto = new AgendamentoResponseDTO();
        dto.setId(agendamento.getId());
        dto.setDataAgendamento(agendamento.getDataAgendamento());
        dto.setEnderecoAgendamento(agendamento.getEnderecoAgendamento());
        dto.setResponsavelAgendamento(agendamento.getResponsavelAgendamento());
        dto.setTelefoneAgendamento(agendamento.getTelefoneAgendamento());
        dto.setTelefoneSecundario(agendamento.getTelefoneSecundario());
        dto.setClienteAgendamento(agendamento.getClienteAgendamento());
        dto.setAgendamentoCidade(agendamento.getAgendamentoCidade());
        dto.setAgendamentoBairro(agendamento.getAgendamentoBairro());

        return dto;
    }

    public static List<AgendamentoResponseDTO> fromEntity(List<Agendamento> agendamentos) {
        return agendamentos.stream().map(AgendamentoResponseDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<AgendamentoResponseDTO> fromEntity(Page<Agendamento> agendamentos) {
        List<AgendamentoResponseDTO> agendamentoL = agendamentos.stream().map(AgendamentoResponseDTO::fromEntity).toList();

        return new PageImpl<>(agendamentoL, agendamentos.getPageable(), agendamentos.getTotalElements());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public void setEnderecoAgendamento(String enderecoAgendamento) {
        this.enderecoAgendamento = enderecoAgendamento;
    }

    public void setResponsavelAgendamento(String responsavelAgendamento) {
        this.responsavelAgendamento = responsavelAgendamento;
    }

    public void setTelefoneAgendamento(String telefoneAgendamento) {
        this.telefoneAgendamento = telefoneAgendamento;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public void setClienteAgendamento(Clientes clienteAgendamento) {
        this.clienteAgendamento = clienteAgendamento;
    }

    public void setAgendamentoCidade(Cidade agendamentoCidade) {
        this.agendamentoCidade = agendamentoCidade;
    }
    public void setAgendamentoBairro(Bairro agendamentoBairro) {
        this.agendamentoBairro = agendamentoBairro;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public String getEnderecoAgendamento() {
        return enderecoAgendamento;
    }

    public String getResponsavelAgendamento() {
        return responsavelAgendamento;
    }

    public String getTelefoneAgendamento() {
        return telefoneAgendamento;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public Clientes getClienteAgendamento() {
        return clienteAgendamento;
    }

    public Cidade getAgendamentoCidade() {
        return agendamentoCidade;
    }

    public Bairro getAgendamentoBairro() {
        return agendamentoBairro;
    }
}
