package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.Bairro;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.models.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class SignupRequest {

    @NotBlank(message = "O nome de usuário não pode estar em branco.")
    @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre 3 e 20 caracteres.")
    private String username;

    @NotBlank(message = "O email não pode estar em branco.")
    @Size(max = 50, message = "O email deve ter no máximo 50 caracteres.")
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40, message = "A senha deve ter entre 6 e 40 caracteres.")
    private String password;

    private Set<String> roles;

    @NotBlank
    @Size(max = 150, message = "Razao Social deve ter no máximo 150 caracteres")
    private String razaoSocial;

    @NotBlank
    @Size(max = 14, message = "CNPJ deve conter no máximo 14 caracteres")
    private String cnpj;

    @Size(max = 14, message = "Inscrição municipal deve conter no máximo 14 caracteres")
    private String inscricaoMunicipal;

    @NotBlank
    @Size(max = 11, message = "Telefone deve conter no máximo 11 caracteres")
    private String telefoneUsuario;

    @NotBlank
    @Size(max = 8, message = "CPE deve conter no máximo 8 caraceteres")
    private String cepUsuario;

    @NotBlank
    @Size(max = 150, message = "Endereço deve ter no máximo 150 caracteres")
    private String enderecoUsuario;

    @NotBlank
    @Size(max = 50)
    private String numeroUsuario;

    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
    private String complementoUsuario;

    private Cidade usuarioCidade;

    private Bairro usuarioBairro;

    // Constructors

    public SignupRequest() {
    }

    public SignupRequest(String username, String email, String password, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public SignupRequest(String username, String email, String password, Set<String> roles, String razaoSocial, String cnpj, String inscricaoMunicipal, String telefoneUsuario, String cepUsuario, String enderecoUsuario, String numeroUsuario, String complementoUsuario, Cidade usuarioCidade, Bairro usuarioBairro) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.telefoneUsuario = telefoneUsuario;
        this.cepUsuario = cepUsuario;
        this.enderecoUsuario = enderecoUsuario;
        this.numeroUsuario = numeroUsuario;
        this.complementoUsuario = complementoUsuario;
        this.usuarioCidade = usuarioCidade;
        this.usuarioBairro = usuarioBairro;
    }

    // Getters and Setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
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

    public Cidade getUsuarioCidade() {
        return usuarioCidade;
    }

    public void setUsuarioCidade(Cidade usuarioCidade) {
        this.usuarioCidade = usuarioCidade;
    }

    public Bairro getUsuarioBairro() {
        return usuarioBairro;
    }

    public void setUsuarioBairro(Bairro usuarioBairro) {
        this.usuarioBairro = usuarioBairro;
    }


}
