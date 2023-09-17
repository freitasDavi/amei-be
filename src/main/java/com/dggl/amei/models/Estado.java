package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ESTADO")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 10)
    @Column(name = "NOME_ESTADO")
    private String nomeEstado;

//    ----

    @JsonIgnore
    @OneToMany(mappedBy = "estadoCidade")
    private List<Cidade> listaCidadeEstado = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioEstado")
    private List<User> listaUsuariosEstado = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "clienteEstado")
    private List<Clientes> listaClientesEstado = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "agendamentoEstado")
    private List<Agendamento> listaAgendamentoEstado = new ArrayList<>();

//    ----

    public Estado() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(id, estado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
