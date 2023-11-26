package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.ItensOrdemServico;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ItemOrdemServicoResponseDTO {

    private Long id;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private Long quantidade;
    private String descricaoItemOrdem;
    private MiniOrdemServicoDTO OrdemDeServico;

    public static ItemOrdemServicoResponseDTO fromEntity(ItensOrdemServico ordemServico) {
        ItemOrdemServicoResponseDTO dto = new ItemOrdemServicoResponseDTO();
        dto.setId(ordemServico.getId());
        dto.setValorUnitario(ordemServico.getValorUnitario());
        dto.setQuantidade(ordemServico.getQuantidade());
        dto.setDescricaoItemOrdem(ordemServico.getDescricaoItemOrdem());
        dto.setOrdemDeServico(new MiniOrdemServicoDTO(ordemServico.getOrdemDeServico().getId()));
        dto.setValorTotal(ordemServico.getValorTotal());


        return dto;
    }

    public static List<ItemOrdemServicoResponseDTO> fromEntity(List<ItensOrdemServico> ordens) {
        return ordens.stream().map(ItemOrdemServicoResponseDTO::fromEntity).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricaoItemOrdem() {
        return descricaoItemOrdem;
    }

    public void setDescricaoItemOrdem(String descricaoItemOrdem) {
        this.descricaoItemOrdem = descricaoItemOrdem;
    }

    public MiniOrdemServicoDTO getOrdemDeServico() {
        return OrdemDeServico;
    }

    public void setOrdemDeServico(MiniOrdemServicoDTO ordemDeServico) {
        OrdemDeServico = ordemDeServico;
    }
}
