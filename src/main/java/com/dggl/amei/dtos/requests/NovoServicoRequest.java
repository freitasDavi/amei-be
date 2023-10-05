package com.dggl.amei.dtos.requests;

import com.dggl.amei.models.User;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class NovoServicoRequest {

    @NotBlank
    @Size(max = 400)
    private String descricaoServico;

    private BigDecimal valorServico;

    private String codigoCNAE;

    private User servicoUsuario;

    public User getServicoUsuario() {
        return servicoUsuario;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public String getCodigoCNAE() {
        return codigoCNAE;
    }
}
