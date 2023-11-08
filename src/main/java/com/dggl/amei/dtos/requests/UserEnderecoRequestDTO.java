package com.dggl.amei.dtos.requests;

import com.dggl.amei.dtos.responses.BairrosResponseDTO;
import com.dggl.amei.models.Bairro;
import com.dggl.amei.models.User;

public class UserEnderecoRequestDTO {
    private Long id;
    private String cepUsuario;
    private String enderecoUsuario;
    private String numeroUsuario;
    private String complementoUsuario;
    private BairrosResponseDTO bairro;

    public void toEntity(User user) {
        user.setCepUsuario(getCepUsuario());
        user.setEnderecoUsuario(getEnderecoUsuario());
        user.setNumeroUsuario(getNumeroUsuario());
        user.setComplementoUsuario(getComplementoUsuario());
        user.setUsuarioBairro(new Bairro(bairro.getId()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCepUsuario() {
        return cepUsuario;
    }

    public void setCepUsuario(String cepUsuario) {
        this.cepUsuario = cepUsuario;
    }

    public String getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public void setEnderecoUsuario(String enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }

    public String getNumeroUsuario() {
        return numeroUsuario;
    }

    public void setNumeroUsuario(String numeroUsuario) {
        this.numeroUsuario = numeroUsuario;
    }

    public String getComplementoUsuario() {
        return complementoUsuario;
    }

    public void setComplementoUsuario(String complementoUsuario) {
        this.complementoUsuario = complementoUsuario;
    }

    public BairrosResponseDTO getBairro() {
        return bairro;
    }

    public void setBairro(BairrosResponseDTO bairro) {
        this.bairro = bairro;
    }
}
