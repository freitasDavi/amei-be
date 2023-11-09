package com.dggl.amei.models;


import com.dggl.amei.models.enums.StatusOrcamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORCAMENTO")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private StatusOrcamentoEnum status;

    @NotBlank
    @Size(max = 11)
    @Column(name = "TELEFONE_CLIENTE")
    private String telefoneCliente;

    @NotBlank
    @Column(name = "NOME_CLIENTE")
    private String nomeCliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @CreationTimestamp
    @Column(name = "DATA_EMISSAO")
    private LocalDateTime dataEmissaoOrcamento;

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

    //    --
    public Orcamento() {
    }

    public Orcamento(String nomeCliente, String telefoneCliente, LocalDate dataValidadeOrcamento, BigDecimal valorTotalDoOrcamento, String observacoesOrcamento, User usuarioOrcamento, Clientes clienteOrcamento, List<ItensOrcamento> itensOrcamentos) {
        this.nomeCliente = nomeCliente;
        this.telefoneCliente = telefoneCliente;
        this.dataValidadeOrcamento = dataValidadeOrcamento;
        this.valorTotalDoOrcamento = valorTotalDoOrcamento;
        this.observacoesOrcamento = observacoesOrcamento;
        this.usuarioOrcamento = usuarioOrcamento;
        this.clienteOrcamento = clienteOrcamento;
        this.itensOrcamentos = itensOrcamentos;
        this.dataEmissaoOrcamento = LocalDateTime.now();
        this.status = StatusOrcamentoEnum.ABERTO;
    }

    public Orcamento(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrcamentoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusOrcamentoEnum status) {
        this.status = status;
    }

    //    public StatusOrcamentoEnum getStatusOrcamento() {
//        return StatusOrcamentoEnum.valueOf(statusOrcamento);
//    }

//    public void setStatusOrcamento(StatusOrcamentoEnum statusOrcamento) {
//        if(statusOrcamento != null){
//            this.statusOrcamento = statusOrcamento;
//        }
//    }

    public String getTelefoneCliente() {
        return telefoneCliente;

    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public LocalDateTime getDataEmissaoOrcamento() {
        return dataEmissaoOrcamento;
    }

    public void setDataEmissaoOrcamento(LocalDateTime dataEmissaoOrcamento) {
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
