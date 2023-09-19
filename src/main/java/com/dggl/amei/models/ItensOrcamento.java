package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.juli.logging.Log;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITENS_ORCAMENTO")
public class ItensOrcamento {

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

    @Size(max = 500)
    @Column(name = "DESCRICAO")
    private String descricao;

//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "itensOrcamento")
    private List<Servico> listaServicosItensOrcamento = new ArrayList<>();

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "ORCAMENTO_ITENS")
    private Orcamento orcamentoItens;

//    ----


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

    public Orcamento getOrcamentoItens() {
        return orcamentoItens;
    }

    public void setOrcamentoItens(Orcamento orcamentoItens) {
        this.orcamentoItens = orcamentoItens;
    }

    public List<Servico> getListaServicosItensOrcamento() {
        return listaServicosItensOrcamento;
    }
}
