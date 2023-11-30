package com.dggl.amei.dtos.requests;

import com.dggl.amei.dtos.responses.AgendamentoResponseDTO;
import com.dggl.amei.dtos.responses.MiniOrcamentoDTO;
import com.dggl.amei.models.Agendamento;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.Orcamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public static ItensOrcamento toEntity(ItemOrcamentoRequestDTO dto) {
        var entidade = new ItensOrcamento();

        entidade.setDescricao(dto.getDescricao());
        entidade.setOrcamento(new Orcamento(dto.getOrcamento().getId()));
        entidade.setQuantidade(dto.getQuantidade());
        entidade.setId(dto.getId());
        entidade.setValorUnitario(dto.getValorUnitario());
        entidade.setValorTotal(dto.getValorTotal());

        return entidade;
    }

    public static List<ItensOrcamento> toEntity(List<ItemOrcamentoRequestDTO> itemList) {
        List<ItensOrcamento> itens = new LinkedList<>();

        for (var i : itemList) {
            itens.add(toEntity(i));
        }

        return itens;
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
