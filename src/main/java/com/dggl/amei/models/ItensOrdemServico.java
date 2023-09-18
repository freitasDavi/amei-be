package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITENS_ORDEM_SERVICO")
public class ItensOrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;

    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Size
    @Column(name = "DESCRICAO")
    private String descricaoItemOrdem;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "ORDEM_ITENS", referencedColumnName = "id")
    private OrdemServico ordemItens;

    @JsonIgnore
    @OneToMany(mappedBy = "itensServico")
    private List<Servico> listaServicoItensOrdem = new ArrayList<>();





}
