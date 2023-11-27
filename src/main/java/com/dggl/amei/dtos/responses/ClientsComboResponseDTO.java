package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public class ClientsComboResponseDTO {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

//    private String cepCliente;
//
//    private String enderecoCliente;
//
//    private String numeroCliente;
//
//    private String complementoCliente;
//
    private String cnpjCliente;
//
//    private String inscricaoMunicipal;

//    ---


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpjCliente() {
        return cnpjCliente;
    }

    public void setCnpjCliente(String cnpjCliente) {
        this.cnpjCliente = cnpjCliente;
    }

    public static ClientsComboResponseDTO fromEntity(Clientes clientes) {
        ClientsComboResponseDTO dto = new ClientsComboResponseDTO();
        dto.setId(clientes.getId());
        dto.setNome(clientes.getNomeCliente());
        dto.setEmail(clientes.getEmailCliente());
        dto.setTelefone(clientes.getTelefoneCliente());
        dto.setCnpjCliente(clientes.getCnpjCliente());
        return dto;
    }

    public Clientes toEntity() {
        Clientes cliente = new Clientes();
        cliente.setId(this.getId());
        cliente.setNomeCliente(this.getNome());
        cliente.setEmailCliente(this.getEmail());
        cliente.setTelefoneCliente(this.getTelefone());
        cliente.setCnpjCliente(this.getCnpjCliente());
        return cliente;
    }

    public static List<ClientsComboResponseDTO> fromEntity(List<Clientes> clientes) {
        return clientes.stream().map(ClientsComboResponseDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<ClientsComboResponseDTO> fromEntity(Page<Clientes> clientes) {
        List<ClientsComboResponseDTO> produtosFind = clientes.stream().map(ClientsComboResponseDTO::fromEntity).collect(Collectors.toList());
        Page<ClientsComboResponseDTO> produtosDTO = new PageImpl<>(produtosFind, clientes.getPageable(), clientes.getTotalElements());
        return produtosDTO;
    }


}
