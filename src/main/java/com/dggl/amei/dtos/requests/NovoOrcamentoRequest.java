package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.Clientes;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.OrdemServico;
import com.dggl.amei.models.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class NovoOrcamentoRequest {

    @Size(max = 11)
    private String telefoneClienteOrcamento;

    //@NotBlank
    private LocalDate dataValidadeOrcamento;

    @NotBlank
    private BigDecimal valorTotalDoOrcamento;

    @Size(max = 500)
    private String observacoesOrcamento;

    private User usuarioOrcamento;

    private Clientes clienteOrcamento;
    private List<ItensOrcamento> orcamentoOrdemServico;

    public String getTelefoneClienteOrcamento() {
        return telefoneClienteOrcamento;
    }

    public LocalDate getDataValidadeOrcamento() {
        return dataValidadeOrcamento;
    }

    public BigDecimal getValorTotalDoOrcamento() {
        return valorTotalDoOrcamento;
    }

    public String getObservacoesOrcamento() {
        return observacoesOrcamento;
    }

    public User getUsuarioOrcamento() {
        return usuarioOrcamento;
    }

    public List<ItensOrcamento> getOrcamentoOrdemServico() {
        return orcamentoOrdemServico;
    }

    public Clientes getClienteOrcamento() {
        return clienteOrcamento;
    }
}
