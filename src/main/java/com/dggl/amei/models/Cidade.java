package com.dggl.amei.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CIDADE")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "NOME_CIDADE")
    private String nomeCidade;

//    --

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CIDADE_ESTADO", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_CIDADE_ESTADO"))
    private Estado estadoCidade;

//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "clienteCidade")
    private List<Clientes> clientesCidade;

    @JsonIgnore
    @OneToMany(mappedBy = "agendamentoCidade")
    private List<Agendamento> agendamentosCidade;

    @JsonIgnore
    @OneToMany(mappedBy = "bairroCidade")
    private List<Bairro> bairrosCidade;

    @JsonIgnore
    @OneToMany(mappedBy = "cidade")
    private List<Curso> cursosCidade;

//    ----

    public Cidade() {
    }

    public Cidade(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getEstadoCidade() {
        return estadoCidade;
    }

    public void setEstadoCidade(Estado estadoCidade) {
        this.estadoCidade = estadoCidade;
    }

    public List<Clientes> getClientesCidade() {
        return clientesCidade;
    }

    public void setClientesCidade(List<Clientes> clientesCidade) {
        this.clientesCidade = clientesCidade;
    }

    public List<Agendamento> getAgendamentosCidade() {
        return agendamentosCidade;
    }

    public void setAgendamentosCidade(List<Agendamento> agendamentosCidade) {
        this.agendamentosCidade = agendamentosCidade;
    }

    public List<Bairro> getBairrosCidade() {
        return bairrosCidade;
    }

    public void setBairrosCidade(List<Bairro> bairrosCidade) {
        this.bairrosCidade = bairrosCidade;
    }

    public List<Curso> getCursosCidade() {
        return cursosCidade;
    }

    public void setCursosCidade(List<Curso> cursosCidade) {
        this.cursosCidade = cursosCidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
