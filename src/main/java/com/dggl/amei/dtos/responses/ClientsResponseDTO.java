package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class ClientsResponseDTO {

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
//    private String cnpjCliente;
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

    public static ClientsResponseDTO fromEntity(Clientes clientes) {
        ClientsResponseDTO dto = new ClientsResponseDTO();
        dto.setId(clientes.getId());
        dto.setNome(clientes.getNomeCliente());
        dto.setEmail(clientes.getEmailCliente());
        dto.setTelefone(clientes.getTelefoneCliente());
        return dto;
    }

    public Clientes toEntity() {
        Clientes cliente = new Clientes();
        cliente.setId(this.getId());
        cliente.setNomeCliente(this.getNome());
        cliente.setEmailCliente(this.getEmail());
        cliente.setTelefoneCliente(this.getTelefone());
        return cliente;
    }

    public static List<ClientsResponseDTO> fromEntity(List<Clientes> clientes) {
        return clientes.stream().map(cliente -> fromEntity(cliente)).collect(Collectors.toList());
    }

    public static Page<ClientsResponseDTO> fromEntity(Page<Clientes> clientes) {
        List<ClientsResponseDTO> produtosFind = clientes.stream().map(cliente -> fromEntity(cliente)).collect(Collectors.toList());
        Page<ClientsResponseDTO> produtosDTO = new PageImpl<>(produtosFind, clientes.getPageable(), clientes.getTotalElements());
        return produtosDTO;
    }


}
