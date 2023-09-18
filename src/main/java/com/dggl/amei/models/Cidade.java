package com.dggl.amei.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CIDADE")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "NOME_CIDADE")
    private String nomeCidade;

//    --

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CIDADE_ESTADO", referencedColumnName = "id")
    private Estado estadoCidade;

    @JsonIgnore
    @OneToMany(mappedBy = "bairroCidade")
    private List<Bairro> listaBairrosCidade = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioCidade")
    private List<User> listaUsuariosCidade = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "clienteCidade")
    private List<Clientes> listaClientesCidade = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "agendamentoCidade")
    private List<Agendamento> listaAgendamentoCidade = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cidadeCurso")
    private List<Curso> listaCursoCidade = new ArrayList<>();

//    --

    public Cidade() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
