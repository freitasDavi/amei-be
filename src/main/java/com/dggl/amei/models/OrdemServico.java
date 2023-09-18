package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDEM_SERVICO")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 11)
    @Column(name = "TELEFONE")
    private String telefoneOrdem;

    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;


//    ----

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "USUARIO_ORDEM", referencedColumnName = "id")
    private User usuarioOrdem;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ORDEM", referencedColumnName = "id")
    private Clientes clienteOrdem;

    @JsonIgnore
    @OneToMany(mappedBy = "orcamentoOrdemServico")
    private List<Orcamento> listaOrcamentosOrdemServico = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "ordemItens")
    private List<ItensOrdemServico> listaItensOrdemServico = new ArrayList<>();

//    ----





}
