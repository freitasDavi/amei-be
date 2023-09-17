package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "CURSO")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "NOME_CURSO")
    private String nomeCurso;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_CURSO")
    private LocalDate dataCurso;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "CIDADE_CURSO", referencedColumnName = "id")
    private Cidade cidadeCurso;
}
