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

//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "clienteCidade")
    private List<Clientes> clientesCidade;

    @JsonIgnore
    @OneToMany(mappedBy = "agendamentoCidade")
    private List<Agendamento> agendamentosCidade;

    @JsonIgnore
    @OneToMany(mappedBy = "bairroCidade")
    private List<Bairro> bairrosCidade;

    @JsonIgnore
    @OneToMany(mappedBy = "cidadeCurso")
    private List<Curso> cursosCidade;

//    ----

    public Cidade() {
    }

    public Cidade(Long id) {
        this.id = id;
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
