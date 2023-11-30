package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "ITENS_ORCAMENTO")
public class ItensOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTIDADE")
    private Long quantidade;

    //@NotBlank
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;

    //@NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Size(max = 500)
    @Column(name = "DESCRICAO")
    private String descricao;

//    ----
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ORCAMENTO_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_ITENS_ORCAMENTO_ORCAMENTO"))
    private Orcamento orcamento;

//    ----

    public ItensOrcamento() {
    }

    public ItensOrcamento(Long id) {
        this.id = id;
    }

    public ItensOrcamento(BigDecimal valorUnitario, BigDecimal valorTotal, String descricao, Long quantidade) {
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.descricao = descricao;
        this.quantidade = quantidade;
        //this.orcamento = orcamento;
    }

    public ItensOrcamento(BigDecimal valorUnitario, BigDecimal valorTotal, String descricao, Orcamento orcamento, Long quantidade) {
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.descricao = descricao;
        this.orcamento = orcamento;
        this.quantidade = quantidade;
    }

    //    ----


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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
}
