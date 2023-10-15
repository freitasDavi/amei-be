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

//    ----

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "ORDEM_ITENS", referencedColumnName = "id")
    private OrdemServico ordemItens;


//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "orcamentoOrdemServico")
    private List<Orcamento> orcamentosOrdemServico;


//  ----


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricaoItemOrdem() {
        return descricaoItemOrdem;
    }

    public void setDescricaoItemOrdem(String descricaoItemOrdem) {
        this.descricaoItemOrdem = descricaoItemOrdem;
    }

    public OrdemServico getOrdemItens() {
        return ordemItens;
    }

    public void setOrdemItens(OrdemServico ordemItens) {
        this.ordemItens = ordemItens;
    }

}
