package com.dggl.amei.dtos.responses.relatorios;

import com.dggl.amei.dtos.responses.ClienteResponseDTO;
import com.dggl.amei.models.ItensOrcamento;
import com.dggl.amei.models.Orcamento;
import com.dggl.amei.models.enums.StatusOrcamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ExportacaoOrcamentoDTO {

    private Long id;
    private String telefoneCliente;
    private String nomeCliente;
    private LocalDateTime dataEmissaoOrcamento;
    private LocalDate dataValidadeOrcamento;
    private BigDecimal valorTotalDoOrcamento;
    private String observacoesOrcamento;
    private List<ItensOrcamento> itensOrcamentos;
    private ClienteResponseDTO cliente;
    private UserDTO usuario;

    public static ExportacaoOrcamentoDTO fromEntity(Orcamento orcamento) {
        var dto = new ExportacaoOrcamentoDTO();

        dto.setId(orcamento.getId());
        dto.setTelefoneCliente(orcamento.getTelefoneCliente());
        dto.setNomeCliente(orcamento.getNomeCliente());
        dto.setDataEmissaoOrcamento(orcamento.getDataEmissaoOrcamento());
        dto.setDataValidadeOrcamento(orcamento.getDataValidadeOrcamento());
        dto.setObservacoesOrcamento(orcamento.getObservacoesOrcamento());
        dto.setItensOrcamentos(orcamento.getItensOrcamentos());
        dto.setCliente(ClienteResponseDTO.fromEntity(orcamento.getClienteOrcamento()));
        dto.setUsuario(UserDTO.fromEntity(orcamento.getUsuarioOrcamento()));
        dto.setValorTotalDoOrcamento(orcamento.getValorTotalDoOrcamento());

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ItensOrcamento> getItensOrcamentos() {
        return itensOrcamentos;
    }

    public void setItensOrcamentos(List<ItensOrcamento> itensOrcamentos) {
        this.itensOrcamentos = itensOrcamentos;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public UserDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UserDTO usuario) {
        this.usuario = usuario;
    }
}
