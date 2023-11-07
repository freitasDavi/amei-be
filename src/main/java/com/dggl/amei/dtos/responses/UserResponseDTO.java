package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.Role;
import com.dggl.amei.models.User;
import com.dggl.amei.models.enums.EnumPlanoAtivo;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private Set<Role> roles = new HashSet<>();
    private String razaoSocialUsuario;
    private String cnpjUsuario;
    private String inscricaoMunicipalUsuario;
    private String telefoneUsuario;
    private String cepUsuario;
    private String enderecoUsuario;
    private String numeroUsuario;
    private String complementoUsuario;
    private EnumPlanoAtivo plano;

    private BairrosResponseDTO bairro;


    public static UserResponseDTO fromEntity(User user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setBairro(BairrosResponseDTO.fromEntity(user.getUsuarioBairro()));
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setCnpjUsuario(user.getCnpjUsuario());
        dto.setCepUsuario(user.getCepUsuario());
        dto.setComplementoUsuario(user.getComplementoUsuario());
        dto.setEnderecoUsuario(user.getEnderecoUsuario());
        dto.setInscricaoMunicipalUsuario(user.getInscricaoMunicipalUsuario());
        dto.setNumeroUsuario(user.getNumeroUsuario());
        dto.setPlano(user.getPlano());
        dto.setRoles(user.getRoles());
        dto.setRazaoSocialUsuario(user.getRazaoSocialUsuario());
        dto.setUsername(user.getUsername());
        dto.setTelefoneUsuario(user.getTelefoneUsuario());

        return dto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public String getInscricaoMunicipalUsuario() {
        return inscricaoMunicipalUsuario;
    }

    public void setInscricaoMunicipalUsuario(String inscricaoMunicipalUsuario) {
        this.inscricaoMunicipalUsuario = inscricaoMunicipalUsuario;
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

    public String getComplementoUsuario() {
        return complementoUsuario;
    }

    public void setComplementoUsuario(String complementoUsuario) {
        this.complementoUsuario = complementoUsuario;
    }

    public EnumPlanoAtivo getPlano() {
        return plano;
    }

    public void setPlano(EnumPlanoAtivo plano) {
        this.plano = plano;
    }

    public BairrosResponseDTO getBairro() {
        return bairro;
    }

    public void setBairro(BairrosResponseDTO bairro) {
        this.bairro = bairro;
    }
}
