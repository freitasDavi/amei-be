package com.dggl.amei.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    private String cpeUsuario;

    @NotBlank
    @Size(max = 30, message = "Cidade deve ter no máximo 30 caracteres")
    private String cidadeUsuario;

    @NotBlank
    @Size(max = 10, message = "Estado deve ter no máximo 10 caracteres")
    private String estadoUsuario;

    @NotBlank
    @Size(max = 150, message = "Lougradouro deve ter no máximo 150 caracteres")
    private String lougradouroUsuario;

    @NotBlank
    @Size(max = 150, message = "Bairro deve ter no máximo 150 caracteres")
    private String bairroUsuario;

    @NotBlank
    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
    private String complementoUsuario;

    private Set<String> role;

    public SignupRequest(String username, String email, Set<String> role, String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

//    ---

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

    public String getCpeUsuario() {
        return cpeUsuario;
    }

    public void setCpeUsuario(String cpeUsuario) {
        this.cpeUsuario = cpeUsuario;
    }

    public String getCidadeUsuario() {
        return cidadeUsuario;
    }

    public void setCidadeUsuario(String cidadeUsuario) {
        this.cidadeUsuario = cidadeUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getLougradouroUsuario() {
        return lougradouroUsuario;
    }

    public void setLougradouroUsuario(String lougradouroUsuario) {
        this.lougradouroUsuario = lougradouroUsuario;
    }

    public String getBairroUsuario() {
        return bairroUsuario;
    }

    public void setBairroUsuario(String bairroUsuario) {
        this.bairroUsuario = bairroUsuario;
    }

    public String getComplementoUsuario() {
        return complementoUsuario;
    }

    public void setComplementoUsuario(String complementoUsuario) {
        this.complementoUsuario = complementoUsuario;
    }


//    ---

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

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
