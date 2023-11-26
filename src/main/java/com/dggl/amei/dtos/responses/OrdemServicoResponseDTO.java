package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrdemServicoResponseDTO {

    private Long id;
    private Integer statusOrdemServico;
    private String telefoneOrdem;
    private BigDecimal valorTotal;
    private LocalDateTime dataEmissaoOrdemServico;
    private User usuarioOrdem;
    private ClientsComboResponseDTO clienteOrdem;
    private List<ItemOrdemServicoResponseDTO> itensOrdemServicos;
    //private List<ItensOrcamento> items;

    public static OrdemServicoResponseDTO fromEntity(OrdemServico ordemServico) {
        OrdemServicoResponseDTO dto = new OrdemServicoResponseDTO();
        dto.setId(ordemServico.getId());
        dto.setStatusOrdemServico(ordemServico.getStatusOrdemServico().getCodigoEnum());
        dto.setTelefoneOrdem(ordemServico.getTelefoneOrdem());
        dto.setValorTotal(ordemServico.getValorTotal());
        dto.setDataEmissaoOrdemServico(ordemServico.getDataEmissaoOrdemServico());
        //dto.setUsuarioOrdem(new User(ordemServico.getUsuarioOrdem().getId()));
        dto.setClienteOrdem(ClientsComboResponseDTO.fromEntity(ordemServico.getClienteOrdem()));
        dto.setItensOrdemServicos(ItemOrdemServicoResponseDTO.fromEntity(ordemServico.getItensOrdemServicos()));

        return dto;
    }

    public static Page<OrdemServicoResponseDTO> fromEntity(Page<OrdemServico> ordemServicos) {
        List<OrdemServicoResponseDTO> ordensServicoList = ordemServicos.stream().map(OrdemServicoResponseDTO::fromEntity).toList();

        return new PageImpl<>(ordensServicoList, ordemServicos.getPageable(), ordemServicos.getTotalElements());
    }

    public LocalDateTime getDataEmissaoOrdemServico() {
        return dataEmissaoOrdemServico;
    }

    public void setDataEmissaoOrdemServico(LocalDateTime dataEmissaoOrdemServico) {
        this.dataEmissaoOrdemServico = dataEmissaoOrdemServico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatusOrdemServico() {
        return statusOrdemServico;
    }

    public void setStatusOrdemServico(Integer statusOrdemServico) {
        this.statusOrdemServico = statusOrdemServico;
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

    public ClientsComboResponseDTO getClienteOrdem() {
        return clienteOrdem;
    }

    public void setClienteOrdem(ClientsComboResponseDTO clienteOrdem) {
        this.clienteOrdem = clienteOrdem;
    }

    public List<ItemOrdemServicoResponseDTO> getItensOrdemServicos() {
        return itensOrdemServicos;
    }

    public void setItensOrdemServicos(List<ItemOrdemServicoResponseDTO> itensOrdemServicos) {
        this.itensOrdemServicos = itensOrdemServicos;
    }
}
