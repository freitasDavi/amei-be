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
        agendamento.setId(this.id);
        agendamento.setNomeAgendamento(this.nomeAgendamento);
        agendamento.setDataAgendamento(this.dataAgendamento);
        agendamento.setEnderecoAgendamento(this.enderecoAgendamento);
        agendamento.setResponsavelAgendamento(this.responsavelAgendamento);
        agendamento.setTelefoneAgendamento(this.telefoneAgendamento);
        agendamento.setTelefoneSecundario(this.telefoneSecundario);
        agendamento.setAgendamentoCidade(new Cidade(this.codigoCidade));
        agendamento.setAgendamentoBairro(new Bairro(this.codigoBairro));
        agendamento.setUsuarioAgendamento(new User(this.codigoUsuario));

        if (this.codigoBairro == 0) {
            agendamento.setClienteAgendamento(new Clientes(this.codigoCliente));
        }

        return agendamento;
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
