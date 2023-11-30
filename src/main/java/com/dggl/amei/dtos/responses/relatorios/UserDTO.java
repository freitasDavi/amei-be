package com.dggl.amei.dtos.responses.relatorios;

import com.dggl.amei.models.Role;
import com.dggl.amei.models.User;
import com.dggl.amei.models.enums.EnumPlanoAtivo;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String razaoSocialUsuario;
    private String cnpjUsuario;
    private String telefoneUsuario;
    private String cepUsuario;
    private String enderecoUsuario;
    private String numeroUsuario;

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setRazaoSocialUsuario(user.getRazaoSocialUsuario());
        dto.setCnpjUsuario(user.getCnpjUsuario());
        dto.setTelefoneUsuario(user.getTelefoneUsuario());
        dto.setCepUsuario(user.getCepUsuario());
        dto.setEnderecoUsuario(user.getEnderecoUsuario());
        dto.setNumeroUsuario(user.getNumeroUsuario());

        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocialUsuario() {
        return razaoSocialUsuario;
    }

    public void setRazaoSocialUsuario(String razaoSocialUsuario) {
        this.razaoSocialUsuario = razaoSocialUsuario;
    }

    public String getCnpjUsuario() {
        return cnpjUsuario;
    }

    public void setCnpjUsuario(String cnpjUsuario) {
        this.cnpjUsuario = cnpjUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getCepUsuario() {
        return cepUsuario;
    }

    public void setCepUsuario(String cepUsuario) {
        this.cepUsuario = cepUsuario;
    }

    public String getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public void setEnderecoUsuario(String enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }

    public String getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(String numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }
}
