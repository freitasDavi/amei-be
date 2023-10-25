package com.dggl.amei.dtos.responses;

import com.dggl.amei.models.Cidade;
import com.dggl.amei.models.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String url;
    private String descricao;
    private LocalDate data;
    private String nomeCidade;
    private Long codigoCidade;

    //    ----

    public static CursoResponseDTO fromEntity(Curso curso) {
        CursoResponseDTO dto = new CursoResponseDTO();

        dto.setId(curso.getId());
        dto.setNome(curso.getNomeCurso());
        dto.setUrl(curso.getUrlCurso());
        dto.setDescricao(curso.getDescricao());
        dto.setData(curso.getDataCurso());
        dto.setNomeCidade(curso.getCidade().getNomeCidade());
        dto.setCodigoCidade(curso.getCidade().getId());

        return dto;
    }

    public Curso toEntity() {
        Curso curso = new Curso();

        curso.setId(this.id);
        curso.setNomeCurso(this.nome);
        curso.setDataCurso(this.data);
        curso.setUrlCurso(this.url);
        curso.setDescricao(this.descricao);
        curso.setCidade(new Cidade(this.codigoCidade));

        return curso;
    }

    public static List<CursoResponseDTO> fromEntity(List<Curso> cursos) {
        return cursos.stream().map(CursoResponseDTO::fromEntity).collect(Collectors.toList());
    }

    public static Page<CursoResponseDTO> fromEntity(Page<Curso> cursos) {
        List<CursoResponseDTO> cursosList = cursos.stream().map(CursoResponseDTO::fromEntity).toList();
        Page<CursoResponseDTO> cursoDTOS = new PageImpl<>(cursosList, cursos.getPageable(), cursos.getTotalElements());

        return cursoDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Long getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(Long codigoCidade) {
        this.codigoCidade = codigoCidade;
    }
}
