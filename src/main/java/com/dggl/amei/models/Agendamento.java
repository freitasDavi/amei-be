package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "AGENDAMENTO")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_AGENDAMENTO")
    private LocalDate dataAgendamento;

    @NotBlank
    @Size(max = 150)
    @Column(name = "ENDERECO")
    private String enderecoAgendamento;

    @Column(name = "RESPONSAVEL")
    private String responsavelAgendamento;

    @Size(max = 11)
    @Column(name = "TELEFONE_PRINCIPAL")
    private String telefoneAgendamento;

    @Size(max = 11)
    @Column(name = "TELEFONE_SECUNDARIO")
    private String telefoneSecundario;

//    ----

    @ManyToOne
    @JoinColumn(name = "CLIENTE_AGENDAMENTO", referencedColumnName = "id")
    private Clientes clienteAgendamento;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CIDADE_AGENDAMENTO", referencedColumnName = "id")
    private Cidade agendamentoCidade;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "BAIRRO_AGENDAMENTO", referencedColumnName = "id")
    private Bairro agendamentoBairro;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "AGENDAMENTO_USUARIO", referencedColumnName = "id")
    private User usuarioAgendamento;

//    ----

    public Agendamento() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento that = (Agendamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
