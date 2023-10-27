package com.dggl.amei.dtos.responses;


import com.dggl.amei.models.Bairro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class BairrosResponseDTO {

    private Long id;

    private String nomeBairro;

    public static BairrosResponseDTO fromEntity(Bairro bairro) {
        BairrosResponseDTO dto = new BairrosResponseDTO();
        dto.setId(bairro.getId());
        dto.setNomeBairro(bairro.getNomeBairro());

        return dto;
    }

    public static Page<BairrosResponseDTO> fromEntity(Page<Bairro> bairros) {
        List<BairrosResponseDTO> bairrosList = bairros.stream().map(BairrosResponseDTO::fromEntity).toList();

        return new PageImpl<>(bairrosList, bairros.getPageable(), bairros.getTotalElements());
    }

    public BairrosResponseDTO() {
    }

    public BairrosResponseDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }
}
