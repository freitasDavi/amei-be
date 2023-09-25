package com.dggl.amei.models;

import com.dggl.amei.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Negative;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns =  @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

//    ---

    @NotBlank
    @Size(max = 100)
    @Column(name = "RAZAO")
    private String razaoSocialUsuario;

    @NotBlank
    @Size(max = 14)
    @Column(name = "CPNJ")
    private String cnpjUsuario;

    @Size(max = 14)
    @Column(name = "INSCRICAO_MUNICIPAL")
    private String inscricaoMunicipalUsuario;

    @NotBlank
    @Size(max = 11)
    @Column(name = "TELEFONE")
    private String telefoneUsuario;

    @NotBlank
    @Size(max = 8)
    @Column(name = "CEP")
    private String cepUsuario;

    @NotBlank
    @Size(max = 150)
    @Column(name = "ENDERECO")
    private String enderecoUsuario;

    @NotBlank
    @Size(max = 50)
    @Column(name = "NUMERO")
    private String numeroUsuario;

    @Size(max = 150)
    @Column(name = "COMPLEMENTO")
    private String complementoUsuario;

//    ---

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CIDADE_USUARIO", referencedColumnName = "id")
    private Cidade usuarioCidade;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "BAIRRO_USUARIO", referencedColumnName = "id")
    private Bairro usuarioBairro;



    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
