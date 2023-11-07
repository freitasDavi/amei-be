package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.Bairro;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.models.Clientes;
import com.dggl.amei.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class ClienteResponseDTO {

    private Long id;
    private String nomeCliente;
    private String emailCliente;
    private String telefoneCliente;
    private String cepCliente;
    private String enderecoCliente;
    private String numeroCliente;
    private String complementoCliente;
    private String cnpjCliente;
    private String inscricaoMunicipal;
    private Long usuarioCliente;
    private Long clienteCidade;
    private BairrosResponseDTO clienteBairro;

    public static ClienteResponseDTO fromEntity(Clientes cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setCepCliente(cliente.getCepCliente());
        dto.setClienteBairro(BairrosResponseDTO.fromEntity(cliente.getClienteBairro()));
        dto.setEmailCliente(cliente.getEmailCliente());
        dto.setCnpjCliente(cliente.getCnpjCliente());
        dto.setClienteCidade(cliente.getClienteCidade().getId());
        dto.setComplementoCliente(cliente.getComplementoCliente());
        dto.setInscricaoMunicipal(cliente.getInscricaoMunicipal());
        dto.setEnderecoCliente(cliente.getEnderecoCliente());
        dto.setNumeroCliente(cliente.getNumeroCliente());
        dto.setTelefoneCliente(cliente.getTelefoneCliente());
        dto.setNomeCliente(cliente.getNomeCliente());
        dto.setUsuarioCliente(cliente.getUsuarioCliente().getId());

        return dto;
    }

    public Clientes toEntity() {
        Clientes clientes = new Clientes();

        clientes.setId(this.id);
        clientes.setCepCliente(this.cepCliente);
        clientes.setEmailCliente(this.emailCliente);
        clientes.setCnpjCliente(this.cnpjCliente);
        clientes.setComplementoCliente(this.complementoCliente);
        clientes.setInscricaoMunicipal(this.inscricaoMunicipal);
        clientes.setEnderecoCliente(this.enderecoCliente);
        clientes.setNomeCliente(this.numeroCliente);
        clientes.setTelefoneCliente(this.telefoneCliente);
        clientes.setNumeroCliente(this.numeroCliente);
        clientes.setUsuarioCliente(new User(this.usuarioCliente));
        clientes.setClienteCidade(new Cidade(this.clienteCidade));
        clientes.setClienteBairro(new Bairro(this.clienteBairro.getId()));

        return clientes;
    }

    public void toEntity(Clientes clientes) {
        clientes.setCepCliente(getCepCliente());
        clientes.setEmailCliente(getEmailCliente());
        clientes.setCnpjCliente(getCnpjCliente());
        clientes.setComplementoCliente(getComplementoCliente());
        clientes.setInscricaoMunicipal(getInscricaoMunicipal());
        clientes.setEnderecoCliente(getEnderecoCliente());
        clientes.setNomeCliente(getNomeCliente());
        clientes.setTelefoneCliente(getTelefoneCliente());
        clientes.setNumeroCliente(getNumeroCliente());
        clientes.setUsuarioCliente(new User(getUsuarioCliente()));
        clientes.setClienteCidade(new Cidade(getClienteCidade()));
        clientes.setClienteBairro(new Bairro(getClienteBairro().getId()));

    }

    public static Page<ClienteResponseDTO> fromEntity(Page<Clientes> clientes)
    {
        List<ClienteResponseDTO> clientsL = clientes.stream().map(ClienteResponseDTO::fromEntity).toList();

        return new PageImpl<>(clientsL, clientes.getPageable(), clientes.getTotalElements());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getComplementoCliente() {
        return complementoCliente;
    }

    public void setComplementoCliente(String complementoCliente) {
        this.complementoCliente = complementoCliente;
    }

    public String getCnpjCliente() {
        return cnpjCliente;
    }

    public void setCnpjCliente(String cnpjCliente) {
        this.cnpjCliente = cnpjCliente;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public Long getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(Long usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public Long getClienteCidade() {
        return clienteCidade;
    }

    public void setClienteCidade(Long clienteCidade) {
        this.clienteCidade = clienteCidade;
    }

    public BairrosResponseDTO getClienteBairro() {
        return clienteBairro;
    }

    public void setClienteBairro(BairrosResponseDTO clienteBairro) {
        this.clienteBairro = clienteBairro;
    }
}
