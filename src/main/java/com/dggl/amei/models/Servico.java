package com.dggl.amei.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 400)
    @Column(name = "DESCRICAO_SERVICO")
    private String descricaoServico;

    @Column(name = "VALOR_SERVICO")
    private BigDecimal valorServico;

    @Column(name = "CODIGO_CNAE")
    private String codigoCNAE;

//    ----

    @ManyToOne
    @JoinColumn(name = "ITENS_ORDEM_SERVICO", referencedColumnName = "id")
    private ItensOrdemServico itensServico;

    @ManyToOne
    @JoinColumn(name = "ITENS_ORCAMENTO", referencedColumnName = "id")
    private ItensOrcamento itensOrcamento;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "SERVICO_USUARIO")
    private User servicoUsuario;

    public Servico() {
    }

    public Servico(Long id) {
        this.id = id;
    }

    public Servico(String descricaoServico, BigDecimal valorServico, String codigoCNAE) {
        this.descricaoServico = descricaoServico;
        this.valorServico = valorServico;
        this.codigoCNAE = codigoCNAE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public String getCodigoCNAE() {
        return codigoCNAE;
    }

    public void setCodigoCNAE(String codigoCNAE) {
        this.codigoCNAE = codigoCNAE;
    }

    public ItensOrdemServico getItensServico() {
        return itensServico;
    }

    public void setItensServico(ItensOrdemServico itensServico) {
        this.itensServico = itensServico;
    }

    public ItensOrcamento getItensOrcamento() {
        return itensOrcamento;
    }

    public void setItensOrcamento(ItensOrcamento itensOrcamento) {
        this.itensOrcamento = itensOrcamento;
    }

    public User getServicoUsuario() {
        return servicoUsuario;
    }

    public void setServicoUsuario(User servicoUsuario) {
        this.servicoUsuario = servicoUsuario;
    }

//    ----

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(id, servico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
