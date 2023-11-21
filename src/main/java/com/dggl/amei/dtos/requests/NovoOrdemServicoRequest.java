package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.Clientes;
import com.dggl.amei.models.ItensOrdemServico;
import com.dggl.amei.models.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class NovoOrdemServicoRequest {

    @Size(max = 11)
    private String telefoneOrdem;

    @NotBlank
    private BigDecimal valorTotal;

    private LocalDateTime dataEmissaoOrdemServico;

    private User usuarioOrdem;

    @NotBlank
    private Clientes clienteOrdem;

    private List<ItensOrdemServico> itensOrdemServicos;

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

    public LocalDateTime getDataEmissaoOrdemServico() {
        return dataEmissaoOrdemServico;
    }

    public void setDataEmissaoOrdemServico(LocalDateTime dataEmissaoOrdemServico) {
        this.dataEmissaoOrdemServico = dataEmissaoOrdemServico;
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
}

