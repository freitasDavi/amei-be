package com.dggl.amei.dtos.responses.relatorios;

public class AgendamentoPorClienteDTO {

    private String nomeCliente;;
    private String cnpjCliente;
    private String telefoneCliente;
    private Long totalAgendamentos;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCnpjCliente() {
        return cnpjCliente;
    }

    public void setCnpjCliente(String cnpjCliente) {
        this.cnpjCliente = cnpjCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public Long getTotalAgendamentos() {
        return totalAgendamentos;
    }

    public void setTotalAgendamentos(Long totalAgendamentos) {
        this.totalAgendamentos = totalAgendamentos;
    }

    public AgendamentoPorClienteDTO(String nomeCliente, String cnpjCliente, String telefoneCliente, Long totalAgendamentos) {
        this.nomeCliente = nomeCliente;
        this.cnpjCliente = cnpjCliente;
        this.telefoneCliente = telefoneCliente;
        this.totalAgendamentos = totalAgendamentos;
    }
}
