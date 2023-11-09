package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.OrdemServico;

import java.util.Optional;

public class UpdateOrcamentoRequest extends NovoOrcamentoRequest {

    private Long id;
    private Optional<OrdemServico> orcamentoOrdemServico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<OrdemServico> getOrcamentoOrdemServico() {
        return orcamentoOrdemServico;
    }

    public void setOrcamentoOrdemServico(Optional<OrdemServico> orcamentoOrdemServico) {
        this.orcamentoOrdemServico = orcamentoOrdemServico;
    }
}
