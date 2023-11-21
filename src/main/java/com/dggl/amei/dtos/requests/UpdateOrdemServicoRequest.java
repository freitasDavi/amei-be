package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.Orcamento;

import java.util.Optional;

public class UpdateOrdemServicoRequest extends NovoOrdemServicoRequest{

    private Long id;

    private Optional<Orcamento> ordemDeServicoOrcamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Orcamento> getOrdemDeServicoOrcamento() {
        return ordemDeServicoOrcamento;
    }

    public void setOrdemDeServicoOrcamento(Optional<Orcamento> ordemDeServicoOrcamento) {
        this.ordemDeServicoOrcamento = ordemDeServicoOrcamento;
    }
}
