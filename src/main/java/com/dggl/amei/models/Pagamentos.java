package com.dggl.amei.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "pagamentos"
)
public class Pagamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALOR_PAGO")
    private Long valorPago;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NOME_CARTAO")
    private String nomeCartao;

    @Column(name = "DATA_PAGAMENTO")
    private LocalDateTime dataPagamento;

    @Column(name = "STRIPE_CUSTOMER_ID")
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "CODIGO_USUARIO", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_USUARIO_PAGAMENTO"))
    private User usuarioPagamento;

    public Pagamentos() {
    }

    public Pagamentos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getValorPago() {
        return valorPago;
    }

    public void setValorPago(Long valorPago) {
        this.valorPago = valorPago;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public User getUsuarioPagamento() {
        return usuarioPagamento;
    }

    public void setUsuarioPagamento(User usuarioPagamento) {
        this.usuarioPagamento = usuarioPagamento;
    }
}
