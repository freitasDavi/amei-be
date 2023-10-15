package com.dggl.amei.models;


import com.dggl.amei.models.enums.StatusOrcamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORCAMENTO")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer statusOrcamento;

    @NotBlank
    @Size(max = 11)
    @Column(name = "TELEFONE_CLIENTE_ORCAMENTO")
    private String telefoneClienteOrcamento;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "HORARIO_GERACAO")
    private Instant dataEmissaoOrcamento;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_VALIDADE")
    private LocalDate dataValidadeOrcamento;

    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotalDoOrcamento;

    @Size(max = 500)
    @Column(name = "OBSERVACAOES")
    private String observacoesOrcamento;

//    ----

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "USUARIO_ORCAMENTO", referencedColumnName = "id")
    private User usuarioOrcamento;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ORCAMENTO", referencedColumnName = "id")
    private Clientes clienteOrcamento;

    @ManyToOne
    @JoinColumn(name = "ORDEM_ORCAMENTO", referencedColumnName = "id")
    private OrdemServico orcamentoOrdemServico;


//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "orcamentoItens")
    private List<ItensOrcamento> itensOrcamentos;

//    ----

    /**
     * Atenção Davi:
     * Quando criar algum construtor, que vá utilizar o status do orcamento, me avisa.
     * ass: Hahn
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrcamentoEnum getStatusOrcamento() {
        return StatusOrcamentoEnum.valueOf(statusOrcamento);
    }

    public void setStatusOrcamento(StatusOrcamentoEnum statusOrcamento) {
        if(statusOrcamento != null){
            this.statusOrcamento = statusOrcamento.getCodigoEnum();
        }
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
