package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORCAMENTO")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 11)
    @Column(name = "TELEFONE_CLIENTE_ORCAMENTO")
    private String telefoneClienteOrcamento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @CreationTimestamp
    @Column(name = "DATA_EMISSAO")
    private Instant dataEmissaoOrcamento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_VALIDADE")
    private LocalDate dataValidadeOrcamento;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotalDoOrcamento;

    @Size(max = 500)
    @Column(name = "OBSERVACAOES")
    private String observacoesOrcamento;

//    ----

    //@NotBlank
    @ManyToOne
    @JoinColumn(name = "USUARIO_ORCAMENTO", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ORCAMENTO_USUARIO"))
    private User usuarioOrcamento;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ORCAMENTO", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ORCAMENTO_CLIENTE"))
    private Clientes clienteOrcamento;

    @ManyToOne
    @JoinColumn(name = "ORDEM_ORCAMENTO", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ORCAMENTO_ORDEMS"))
    private OrdemServico orcamentoOrdemServico;

//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "orcamento")
    private List<ItensOrcamento> itensOrcamentos;


    //    ----

    public Orcamento() {
    }

    public Orcamento(String telefoneClienteOrcamento, LocalDate dataValidadeOrcamento, BigDecimal valorTotalDoOrcamento, String observacoesOrcamento, User usuarioOrcamento, Clientes clienteOrcamento, List<ItensOrcamento> itensOrcamentos) {
        this.telefoneClienteOrcamento = telefoneClienteOrcamento;
        this.dataValidadeOrcamento = dataValidadeOrcamento;
        this.valorTotalDoOrcamento = valorTotalDoOrcamento;
        this.observacoesOrcamento = observacoesOrcamento;
        this.usuarioOrcamento = usuarioOrcamento;
        this.clienteOrcamento = clienteOrcamento;
        this.itensOrcamentos = itensOrcamentos;
        this.dataEmissaoOrcamento = Instant.now();
    }

    public Orcamento(Long id) {
        this.id = id;
    }

    // --

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefoneClienteOrcamento() {
        return telefoneClienteOrcamento;
    }

    public void setTelefoneClienteOrcamento(String telefoneClienteOrcamento) {
        this.telefoneClienteOrcamento = telefoneClienteOrcamento;
    }

    public Instant getDataEmissaoOrcamento() {
        return dataEmissaoOrcamento;
    }

    public void setDataEmissaoOrcamento(Instant dataEmissaoOrcamento) {
        this.dataEmissaoOrcamento = dataEmissaoOrcamento;
    }

    public LocalDate getDataValidadeOrcamento() {
        return dataValidadeOrcamento;
    }

    public void setDataValidadeOrcamento(LocalDate dataValidadeOrcamento) {
        this.dataValidadeOrcamento = dataValidadeOrcamento;
    }

    public BigDecimal getValorTotalDoOrcamento() {
        return valorTotalDoOrcamento;
    }

    public void setValorTotalDoOrcamento(BigDecimal valorTotalDoOrcamento) {
        this.valorTotalDoOrcamento = valorTotalDoOrcamento;
    }

    public String getObservacoesOrcamento() {
        return observacoesOrcamento;
    }

    public void setObservacoesOrcamento(String observacoesOrcamento) {
        this.observacoesOrcamento = observacoesOrcamento;
    }

    public User getUsuarioOrcamento() {
        return usuarioOrcamento;
    }

    public void setUsuarioOrcamento(User usuarioOrcamento) {
        this.usuarioOrcamento = usuarioOrcamento;
    }

    public Clientes getClienteOrcamento() {
        return clienteOrcamento;
    }

    public void setClienteOrcamento(Clientes clienteOrcamento) {
        this.clienteOrcamento = clienteOrcamento;
    }

    public OrdemServico getOrcamentoOrdemServico() {
        return orcamentoOrdemServico;
    }

    public void setOrcamentoOrdemServico(OrdemServico orcamentoOrdemServico) {
        this.orcamentoOrdemServico = orcamentoOrdemServico;
    }

    public List<ItensOrcamento> getItensOrcamentos() {
        return itensOrcamentos;
    }

    public void setItensOrcamentos(List<ItensOrcamento> itensOrcamentos) {
        this.itensOrcamentos = itensOrcamentos;
    }

    //    ----


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orcamento orcamento = (Orcamento) o;
        return Objects.equals(id, orcamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
