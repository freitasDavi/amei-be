package com.dggl.amei.dtos.requests;

import com.dggl.amei.dtos.responses.AgendamentoResponseDTO;
import com.dggl.amei.models.*;

import java.time.LocalDate;

public class AgendamentoRequestDTO {
    private Long id;

    private String nomeAgendamento;

    private LocalDate dataAgendamento;

    private String enderecoAgendamento;

    private String responsavelAgendamento;

    private String telefoneAgendamento;

    private String telefoneSecundario;

    private Long codigoCliente;

    private Long codigoCidade;

    private Long codigoBairro;
    private Long codigoUsuario;

    public Agendamento toEntity() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(getId());
        agendamento.setNomeAgendamento(getNomeAgendamento());
        agendamento.setDataAgendamento(getDataAgendamento());
        agendamento.setEnderecoAgendamento(getEnderecoAgendamento());
        agendamento.setResponsavelAgendamento(getResponsavelAgendamento());
        agendamento.setTelefoneAgendamento(getTelefoneAgendamento());
        agendamento.setTelefoneSecundario(getTelefoneSecundario());
        agendamento.setAgendamentoCidade(new Cidade(getCodigoCidade()));
        agendamento.setAgendamentoBairro(new Bairro(getCodigoBairro()));
        agendamento.setUsuarioAgendamento(new User(getCodigoUsuario()));

        if (this.codigoBairro == 0) {
            agendamento.setClienteAgendamento(new Clientes(getCodigoCliente()));
        }

        return agendamento;
    }

    public void toEntity(Agendamento agendamento) {
        agendamento.setNomeAgendamento(getNomeAgendamento());
        agendamento.setDataAgendamento(getDataAgendamento());
        agendamento.setEnderecoAgendamento(getEnderecoAgendamento());
        agendamento.setResponsavelAgendamento(getResponsavelAgendamento());
        agendamento.setTelefoneAgendamento(getTelefoneAgendamento());
        agendamento.setTelefoneSecundario(getTelefoneSecundario());
        agendamento.setAgendamentoCidade(new Cidade(getCodigoCidade()));
        agendamento.setAgendamentoBairro(new Bairro(getCodigoBairro()));
        agendamento.setUsuarioAgendamento(new User(getCodigoUsuario()));

        if (getCodigoCliente() != 0) {
            agendamento.setClienteAgendamento(new Clientes(getCodigoCliente()));
        }
    }

    public Long getId() {
        return id;
    }

    public String getNomeAgendamento() {
        return nomeAgendamento;
    }

    public void setNomeAgendamento(String nomeAgendamento) {
        this.nomeAgendamento = nomeAgendamento;
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

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public Long getCodigoCidade() {
        return codigoCidade;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}
