package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ITENS_ORDEM_SERVICO")
public class ItensOrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @NotBlank
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;

//    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Column(name = "QUANTIDADE")
    private Long quantidade;

    @Size
    @Column(name = "DESCRICAO")
    private String descricaoItemOrdem;

//    ----

//    @NotBlank
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ORDEM_ITENS", referencedColumnName = "id")
    private OrdemServico OrdemDeServico;


//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "orcamentoOrdemServico")
    private List<Orcamento> orcamentosOrdemServico;


//  ----

    public ItensOrdemServico(Long quantidade, BigDecimal valorUnitario, BigDecimal valorTotal, String descricaoItemOrdem, OrdemServico ordemDeServico) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.descricaoItemOrdem = descricaoItemOrdem;
        OrdemDeServico = ordemDeServico;
    }

    public ItensOrdemServico() {

    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

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

    public OrdemServico getOrdemDeServico() {
        return OrdemDeServico;
    }

    public void setOrdemDeServico(OrdemServico ordemItens) {
        this.OrdemDeServico = ordemItens;
    }

}
