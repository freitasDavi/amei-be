package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.Bairro;
import com.dggl.amei.models.Cidade;

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
    private String cepUsuario;

    private Cidade cidadeUsuario;

    @NotBlank
    @Size(max = 150, message = "Logradouro deve ter no máximo 150 caracteres")
    private String logradouroUsuario;

    private Bairro bairroUsuario;

    @NotBlank
    @Size(max = 100, message = "Complemento deve ter no máximo 100 caracteres")
    private String complementoUsuario;

    private String numeroUsuario;

    private Set<String> role;

    public SignupRequest(String username, String email, Set<String> role, String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
    }

//    ---


    public String getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(String numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
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

    public String getCpeUsuario() {
        return cepUsuario;
    }

    public void setCepUsuario(String cpeUsuario) {
        this.cepUsuario = cpeUsuario;
    }

    public Cidade getCidadeUsuario() {
        return cidadeUsuario;
    }

    public void setCidadeUsuario(Cidade cidadeUsuario) {
        this.cidadeUsuario = cidadeUsuario;
    }


    public String getLogradouroUsuario() {
        return logradouroUsuario;
    }

    public void setLogradouroUsuario(String lougradouroUsuario) {
        this.logradouroUsuario = lougradouroUsuario;
    }

    public Bairro getBairroUsuario() {
        return bairroUsuario;
    }

    public void setBairroUsuario(Bairro bairroUsuario) {
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
