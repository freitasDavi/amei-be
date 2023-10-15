package com.dggl.amei.models;

import com.dggl.amei.models.enums.StatusOrcamentoEnum;
import com.dggl.amei.models.enums.StatusOrdemServicoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDEM_SERVICO")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer statusOrdemServico;

    @Size(max = 11)
    @Column(name = "TELEFONE")
    private String telefoneOrdem;

    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;


//    ----

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "USUARIO_ORDEM", referencedColumnName = "id")
    private User usuarioOrdem;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ORDEM", referencedColumnName = "id")
    private Clientes clienteOrdem;

//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "ordemItens")
    private List<ItensOrdemServico> itensOrdemServicos;


//    ----


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public StatusOrdemServicoEnum getStatusOrdemServico() {
        return StatusOrdemServicoEnum.valueOf(statusOrdemServico);
    }

    public void setStatusOrdemServico(StatusOrdemServicoEnum statusOrdemServico) {
        if(statusOrdemServico != null){
            this.statusOrdemServico = statusOrdemServico.getCodigoEnum();
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
