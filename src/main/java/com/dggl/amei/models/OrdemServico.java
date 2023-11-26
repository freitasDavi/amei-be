package com.dggl.amei.models;

import com.dggl.amei.models.enums.StatusOrdemServicoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDEM_SERVICO")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private StatusOrdemServicoEnum statusOrdemServico;

    @Size(max = 11)
    @Column(name = "TELEFONE")
    private String telefoneOrdem;

//    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

//    @NotBlank
    @ManyToOne
    @JoinColumn(name = "USUARIO_ORDEM", referencedColumnName = "id")
    private User usuarioOrdem;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ORDEM", referencedColumnName = "id")
    private Clientes clienteOrdem;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @CreationTimestamp
    @Column(name = "DATA_EMISSAO_ORDEM")
    private LocalDateTime dataEmissaoOrdemServico;

//    ----

    @OneToMany(mappedBy = "OrdemDeServico")
    private List<ItensOrdemServico> itensOrdemServicos;

    public OrdemServico() {

    }


    public OrdemServico(Clientes clienteOrdem, String telefoneOrdem, BigDecimal valorTotal, User usuarioOrdem,  LocalDateTime dataEmissaoOrdemServico) {
        this.clienteOrdem = clienteOrdem;
        this.telefoneOrdem = telefoneOrdem;
        this.valorTotal = valorTotal;
        this.usuarioOrdem = usuarioOrdem;
        this.dataEmissaoOrdemServico = LocalDateTime.now();
        this.statusOrdemServico = StatusOrdemServicoEnum.AGUARDANDO_EMISSAO;
    }

    public OrdemServico(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrdemServicoEnum getStatusOrdemServico() {
        return statusOrdemServico;
    }

    public void setStatusOrdemServico(StatusOrdemServicoEnum statusOrdemServico) {
        if(statusOrdemServico != null){
            this.statusOrdemServico = statusOrdemServico;
        }
    }

    public String getTelefoneOrdem() {
        return telefoneOrdem;
    }

    public void setTelefoneOrdem(String telefoneOrdem) {
        this.telefoneOrdem = telefoneOrdem;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public User getUsuarioOrdem() {
        return usuarioOrdem;
    }

    public void setUsuarioOrdem(User usuarioOrdem) {
        this.usuarioOrdem = usuarioOrdem;
    }

    public Clientes getClienteOrdem() {
        return clienteOrdem;
    }

    public void setClienteOrdem(Clientes clienteOrdem) {
        this.clienteOrdem = clienteOrdem;
    }

    public List<ItensOrdemServico> getItensOrdemServicos() {
        return itensOrdemServicos;
    }

    public void setItensOrdemServicos(List<ItensOrdemServico> itensOrdemServicos) {
        this.itensOrdemServicos = itensOrdemServicos;
    }

    public LocalDateTime getDataEmissaoOrdemServico() {
        return dataEmissaoOrdemServico;
    }

    public void setDataEmissaoOrdemServico(LocalDateTime dataEmissaoOrdemServico) {
        this.dataEmissaoOrdemServico = dataEmissaoOrdemServico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
