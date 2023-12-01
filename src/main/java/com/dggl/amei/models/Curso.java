package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

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
    @Column(name = "URL_CURSO")
    private String urlCurso;

    @NotBlank
    @Column(name = "DESCRICAO")
    private String descricao;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_CURSO")
    private LocalDate dataCurso;

//    ----

    @ManyToOne
    @JoinColumn(name = "CODIGOCIDADE", referencedColumnName = "id")
    private Cidade cidade;

//    ----


    public Curso() {
    }

    public Curso(Long id) {
        this.id = id;
    }

    public Curso(String nomeCurso, String urlCurso, String descricao, LocalDate dataCurso, Cidade cidade) {
        this.nomeCurso = nomeCurso;
        this.urlCurso = urlCurso;
        this.descricao = descricao;
        this.dataCurso = dataCurso;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getUrlCurso() {
        return urlCurso;
    }

    public void setUrlCurso(String urlCurso) {
        this.urlCurso = urlCurso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCurso() {
        return dataCurso;
    }

    public void setDataCurso(LocalDate dataCurso) {
        this.dataCurso = dataCurso;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
