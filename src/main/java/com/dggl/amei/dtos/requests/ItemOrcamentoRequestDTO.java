package com.dggl.amei.dtos.requests;

import com.dggl.amei.dtos.responses.MiniOrcamentoDTO;

import java.math.BigDecimal;

public class ItemOrcamentoRequestDTO {
    private Long id;
    private Long quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private String descricao;
    private MiniOrcamentoDTO orcamento;

    public static ItemOrcamentoRequestDTO fromEntity(ItemOrcamentoRequestDTO item) {
        var dto = new ItemOrcamentoRequestDTO();

        dto.setId(item.getId());
        dto.setQuantidade(item.getQuantidade());
        dto.setValorUnitario(item.getValorUnitario());
        dto.setValorTotal(item.getValorTotal());
        dto.setDescricao(item.getDescricao());
        dto.setOrcamento(new MiniOrcamentoDTO(item.getOrcamento().getId()));

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MiniOrcamentoDTO getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(MiniOrcamentoDTO orcamento) {
        this.orcamento = orcamento;
    }
}
