package com.dggl.amei.dtos.requests;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class NovoServicoRequest {

    @NotBlank
    @Size(max = 400)
    private String descricao;

    private BigDecimal valor;

    private String codigoCNAE;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getCodigoCNAE() {
        return codigoCNAE;
    }
}
