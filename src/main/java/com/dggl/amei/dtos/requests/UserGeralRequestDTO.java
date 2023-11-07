package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.User;

public class UserGeralRequestDTO {
    private Long id;
    private String username;
    private String email;
    private String razaoSocialUsuario;
    private String cnpjUsuario;
    private String inscricaoMunicipalUsuario;
    private String telefoneUsuario;

    public void toEntity(User user) {
        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setRazaoSocialUsuario(getRazaoSocialUsuario());
        user.setCnpjUsuario(getCnpjUsuario());
        user.setInscricaoMunicipalUsuario(getInscricaoMunicipalUsuario());
        user.setTelefoneUsuario(getTelefoneUsuario());
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
}
