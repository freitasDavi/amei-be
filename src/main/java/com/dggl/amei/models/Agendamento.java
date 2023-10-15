package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "AGENDAMENTO")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_AGENDAMENTO")
    private LocalDate dataAgendamento;

    @NotBlank
    @Size(max = 150)
    @Column(name = "ENDERECO")
    private String enderecoAgendamento;

    @Column(name = "RESPONSAVEL")
    private String responsavelAgendamento;

    @Size(max = 11)
    @Column(name = "TELEFONE_PRINCIPAL")
    private String telefoneAgendamento;

    @Size(max = 11)
    @Column(name = "TELEFONE_SECUNDARIO")
    private String telefoneSecundario;

//    ----

    @ManyToOne
    @JoinColumn(name = "CLIENTE_AGENDAMENTO", referencedColumnName = "id")
    private Clientes clienteAgendamento;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CIDADE_AGENDAMENTO", referencedColumnName = "id")
    private Cidade agendamentoCidade;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "BAIRRO_AGENDAMENTO", referencedColumnName = "id")
    private Bairro agendamentoBairro;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "AGENDAMENTO_USUARIO", referencedColumnName = "id")
    private User usuarioAgendamento;

//    ----


//    ----

    public Agendamento() {
    }

//    ----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getEnderecoAgendamento() {
        return enderecoAgendamento;
    }

    public void setEnderecoAgendamento(String enderecoAgendamento) {
        this.enderecoAgendamento = enderecoAgendamento;
    }

    public String getResponsavelAgendamento() {
        return responsavelAgendamento;
    }

    public void setResponsavelAgendamento(String responsavelAgendamento) {
        this.responsavelAgendamento = responsavelAgendamento;
    }

    public String getTelefoneAgendamento() {
        return telefoneAgendamento;
    }

    public void setTelefoneAgendamento(String telefoneAgendamento) {
        this.telefoneAgendamento = telefoneAgendamento;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    public Clientes getClienteAgendamento() {
        return clienteAgendamento;
    }

    public void setClienteAgendamento(Clientes clienteAgendamento) {
        this.clienteAgendamento = clienteAgendamento;
    }

    public Cidade getAgendamentoCidade() {
        return agendamentoCidade;
    }

    public void setAgendamentoCidade(Cidade agendamentoCidade) {
        this.agendamentoCidade = agendamentoCidade;
    }

    public Bairro getAgendamentoBairro() {
        return agendamentoBairro;
    }

    public void setAgendamentoBairro(Bairro agendamentoBairro) {
        this.agendamentoBairro = agendamentoBairro;
    }

    public User getUsuarioAgendamento() {
        return usuarioAgendamento;
    }

    public void setUsuarioAgendamento(User usuarioAgendamento) {
        this.usuarioAgendamento = usuarioAgendamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
