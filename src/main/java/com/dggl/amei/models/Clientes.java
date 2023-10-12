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

    @NotBlank
    @Size(max = 13)
    @Column(name = "CNPJ")
    private String cnpjCliente;

    @Size(max = 14)
    @Column(name = "INSCRICAO_MUNICIPAL")
    private String inscricaoMunicipal;

//    ---

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CLIENTE_USUARIO", referencedColumnName = "id")
    private User usuarioCliente;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CIDADE_CLIENTE", referencedColumnName = "id")
    private Cidade clienteCidade;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "BAIRRO_CLIENTE", referencedColumnName = "id")
    private Bairro clienteBairro;

//    ---

    @JsonIgnore
    @OneToMany(mappedBy = "clienteAgendamento")
    private List<Agendamento> agendamentosCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteOrcamento")
    private List<Orcamento> orcamentosCliente;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteOrdem")
    private List<OrdemServico> ordemServicosCliente;

//    ---

    public Clientes() {
    }

    public Clientes(Long id) {
        this.id = id;
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
