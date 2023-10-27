package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "CLIENTES")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "NOME")
    private String nomeCliente;

    @NotBlank
    @Size(max = 50)
    @Column(name = "EMAIL")
    @Email
    private String emailCliente;

    @NotBlank
    @Size(max = 11)
    @Column(name = "TELEFONE")
    private String telefoneCliente;

    @NotBlank
    @Size(max = 8)
    @Column(name = "CEP")
    private String cepCliente;

    @NotBlank
    @Size(max = 150)
    @Column(name = "ENDERECO")
    private String enderecoCliente;

    @NotBlank
    @Size(max = 50)
    @Column(name = "NUMERO")
    private String numeroCliente;

    @NotBlank
    @Size(max = 150)
    @Column(name = "COMPLEMENTO")
    private String complementoCliente;

    @Size(max = 13)
    @Column(name = "CNPJ")
    private String cnpjCliente;

    @Size(max = 14)
    @Column(name = "INSCRICAO_MUNICIPAL")
    private String inscricaoMunicipal;

//    ---

    @ManyToOne
    @JoinColumn(name = "CLIENTE_USUARIO", referencedColumnName = "id")
    private User usuarioCliente;

    @ManyToOne
    @JoinColumn(name = "CIDADE_CLIENTE", referencedColumnName = "id")
    private Cidade clienteCidade;

    @ManyToOne
    @JoinColumn(name = "BAIRRO_CLIENTE", referencedColumnName = "id")
    private Bairro clienteBairro;

//    ---

    @JsonIgnore
    @OneToMany(mappedBy = "clienteOrcamento")
    private List<Orcamento> orcamentosCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteAgendamento")
    private List<Agendamento> clientesAgendamento;

//    ---

    public Clientes() {
    }

    public Clientes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getComplementoCliente() {
        return complementoCliente;
    }

    public void setComplementoCliente(String complementoCliente) {
        this.complementoCliente = complementoCliente;
    }

    public String getCnpjCliente() {
        return cnpjCliente;
    }

    public void setCnpjCliente(String cnpjCliente) {
        this.cnpjCliente = cnpjCliente;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public User getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(User usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public Cidade getClienteCidade() {
        return clienteCidade;
    }

    public void setClienteCidade(Cidade clienteCidade) {
        this.clienteCidade = clienteCidade;
    }

    public Bairro getClienteBairro() {
        return clienteBairro;
    }

    public void setClienteBairro(Bairro clienteBairro) {
        this.clienteBairro = clienteBairro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes = (Clientes) o;
        return Objects.equals(id, clientes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
