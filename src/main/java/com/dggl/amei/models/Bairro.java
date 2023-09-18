package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BAIRRO")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "NOME_BAIRRO")
    private String nomeBairro;

//    ---

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "BAIRRO_CIDADE", referencedColumnName = "id")
    private Cidade cidadeBairro;

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioBairro")
    private List<User> listaUsuariosBairro = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "clienteBairro")
    private List<Clientes> listaClientesBairro = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "agendamentoBairro")
    private List<Agendamento> listaAgendamentoBairro = new ArrayList<>();

//    ---

    public Bairro() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bairro bairro = (Bairro) o;
        return Objects.equals(id, bairro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}