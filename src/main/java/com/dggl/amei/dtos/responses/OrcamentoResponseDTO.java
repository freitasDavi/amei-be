package com.dggl.amei.dtos.responses;

import com.dggl.amei.dtos.responses.relatorios.UserDTO;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.models.enums.StatusOrcamentoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrcamentoResponseDTO {

    private Long id;
    private StatusOrcamentoEnum status;
    private String telefoneCliente;
    private String nomeCliente;
    private LocalDateTime dataEmissaoOrcamento;
    private LocalDate dataValidadeOrcamento;
    private BigDecimal valorTotalDoOrcamento;
    private String observacoesOrcamento;
    private UserDTO usuarioOrcamento;
    private ClienteResponseDTO clienteOrcamento;
    private List<ItensOrcamento> itensOrcamentos;

    public static OrcamentoResponseDTO fromEntity (Orcamento orcamento) {
        var dto = new OrcamentoResponseDTO();

        dto.setId(orcamento.getId());
        dto.setStatus(orcamento.getStatus());
        dto.setTelefoneCliente(orcamento.getTelefoneCliente());
        dto.setNomeCliente(orcamento.getNomeCliente());
        dto.setDataEmissaoOrcamento(orcamento.getDataEmissaoOrcamento());
        dto.setDataValidadeOrcamento(orcamento.getDataValidadeOrcamento());
        dto.setValorTotalDoOrcamento(orcamento.getValorTotalDoOrcamento());
        dto.setObservacoesOrcamento(orcamento.getObservacoesOrcamento());
        dto.setUsuarioOrcamento(UserDTO.fromEntity(orcamento.getUsuarioOrcamento()));
        dto.setClienteOrcamento(ClienteResponseDTO.fromEntity(orcamento.getClienteOrcamento()));
        dto.setItensOrcamentos(orcamento.getItensOrcamentos());

        return dto;
    }

    public static Page<OrcamentoResponseDTO> fromEntity(Page<Orcamento> orcamentos) {
        List<OrcamentoResponseDTO> orcamentoResponseDTOList = orcamentos.stream().map(OrcamentoResponseDTO::fromEntity).toList();

        return new PageImpl<>(orcamentoResponseDTOList, orcamentos.getPageable(), orcamentos.getTotalElements());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusOrcamentoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusOrcamentoEnum status) {
        this.status = status;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public LocalDateTime getDataEmissaoOrcamento() {
        return dataEmissaoOrcamento;
    }

    public void setDataEmissaoOrcamento(LocalDateTime dataEmissaoOrcamento) {
        this.dataEmissaoOrcamento = dataEmissaoOrcamento;
    }

    public LocalDate getDataValidadeOrcamento() {
        return dataValidadeOrcamento;
    }

    public void setDataValidadeOrcamento(LocalDate dataValidadeOrcamento) {
        this.dataValidadeOrcamento = dataValidadeOrcamento;
    }

    public BigDecimal getValorTotalDoOrcamento() {
        return valorTotalDoOrcamento;
    }

    public void setValorTotalDoOrcamento(BigDecimal valorTotalDoOrcamento) {
        this.valorTotalDoOrcamento = valorTotalDoOrcamento;
    }

    public String getObservacoesOrcamento() {
        return observacoesOrcamento;
    }

    public void setObservacoesOrcamento(String observacoesOrcamento) {
        this.observacoesOrcamento = observacoesOrcamento;
    }

    public UserDTO getUsuarioOrcamento() {
        return usuarioOrcamento;
    }

    public void setUsuarioOrcamento(UserDTO usuarioOrcamento) {
        this.usuarioOrcamento = usuarioOrcamento;
    }

    public ClienteResponseDTO getClienteOrcamento() {
        return clienteOrcamento;
    }

    public void setClienteOrcamento(ClienteResponseDTO clienteOrcamento) {
        this.clienteOrcamento = clienteOrcamento;
    }

    public List<ItensOrcamento> getItensOrcamentos() {
        return itensOrcamentos;
    }

    public void setItensOrcamentos(List<ItensOrcamento> itensOrcamentos) {
        this.itensOrcamentos = itensOrcamentos;
    }
}
