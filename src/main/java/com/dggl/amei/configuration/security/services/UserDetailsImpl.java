package com.dggl.amei.configuration.security.services;

import com.dggl.amei.models.Bairro;
import com.dggl.amei.models.Cidade;
import com.dggl.amei.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    private String razaoSocial;

    private String cnpj;

    private String inscricaoMunicipal;

    private String telefoneUsuario;

    private String cepUsuario;

    private Cidade cidadeUsuario;

//    private String estadoUsuario;

    private String lougradouroUsuario;

    private Bairro bairroUsuario;

    private String complementoUsuario;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities,
                           Bairro bairroUsuario, String cnpj, Cidade cidadeUsuario, String razaoSocial,
                           String complementoUsuario, String cepUsuario,
                           String inscricaoMunicipal, String telefoneUsuario, String lougradouroUsuario
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.bairroUsuario = bairroUsuario;
        this.cnpj = cnpj;
        this.cidadeUsuario = cidadeUsuario;
        this.razaoSocial = razaoSocial;
        this.complementoUsuario = complementoUsuario;
        this.cepUsuario = cepUsuario;
//        this.estadoUsuario = estadoUsuario;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.telefoneUsuario = telefoneUsuario;
        this.lougradouroUsuario = lougradouroUsuario;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.getUsuarioBairro(),
                user.getCnpjUsuario(),
                user.getUsuarioCidade(),
                user.getRazaoSocialUsuario(),
                user.getComplementoUsuario(),
                user.getCepUsuario(),
                user.getInscricaoMunicipalUsuario(),
                user.getTelefoneUsuario(),
                user.getLougradouroUsuario()
                );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public void setCepUsuario(String cpeUsuario) {
        this.cepUsuario = cpeUsuario;
    }

    public Cidade getCidadeUsuario() {
        return cidadeUsuario;
    }

    public void setCidadeUsuario(Cidade cidadeUsuario) {
        this.cidadeUsuario = cidadeUsuario;
    }

    public String getLougradouroUsuario() {
        return lougradouroUsuario;
    }

    public void setLougradouroUsuario(String lougradouroUsuario) {
        this.lougradouroUsuario = lougradouroUsuario;
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


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
