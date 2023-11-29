package com.dggl.amei.dtos.responses.relatorios;

import com.dggl.amei.dtos.responses.ClienteResponseDTO;
import com.dggl.amei.dtos.responses.ItemOrdemServicoResponseDTO;
import com.dggl.amei.models.OrdemServico;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ExportacaoOrdemDTO {
    private Long id;
    private String telefoneOrdem;
    private BigDecimal valorTotal;
    private LocalDateTime dataEmissaoOrdemServico;
    private UserDTO usuarioOrdem;
    private ClienteResponseDTO clienteOrdem;
    private List<ItemOrdemServicoResponseDTO> itensOrdemServicos;

    public static ExportacaoOrdemDTO fromEntity(OrdemServico ordem) {
        var dto = new ExportacaoOrdemDTO();

        dto.setId(ordem.getId());
        dto.setTelefoneOrdem(ordem.getTelefoneOrdem());
        dto.setValorTotal(ordem.getValorTotal());
        dto.setDataEmissaoOrdemServico(ordem.getDataEmissaoOrdemServico());
        dto.setUsuarioOrdem(UserDTO.fromEntity(ordem.getUsuarioOrdem()));
        dto.setClienteOrdem(ClienteResponseDTO.fromEntity(ordem.getClienteOrdem()));
        dto.setItensOrdemServicos(ItemOrdemServicoResponseDTO.fromEntity(ordem.getItensOrdemServicos()));

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getDataEmissaoOrdemServico() {
        return dataEmissaoOrdemServico;
    }

    public void setDataEmissaoOrdemServico(LocalDateTime dataEmissaoOrdemServico) {
        this.dataEmissaoOrdemServico = dataEmissaoOrdemServico;
    }

    public UserDTO getUsuarioOrdem() {
        return usuarioOrdem;
    }

    public void setUsuarioOrdem(UserDTO usuarioOrdem) {
        this.usuarioOrdem = usuarioOrdem;
    }

    public ClienteResponseDTO getClienteOrdem() {
        return clienteOrdem;
    }

    public void setClienteOrdem(ClienteResponseDTO clienteOrdem) {
        this.clienteOrdem = clienteOrdem;
    }

    public List<ItemOrdemServicoResponseDTO> getItensOrdemServicos() {
        return itensOrdemServicos;
    }

    public void setItensOrdemServicos(List<ItemOrdemServicoResponseDTO> itensOrdemServicos) {
        this.itensOrdemServicos = itensOrdemServicos;
    }
}
