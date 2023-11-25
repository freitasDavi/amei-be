package com.dggl.amei.dtos.responses;

public class RetornoCEP {

    private Long codigoCidade;
    private Long codigoBairro;
    private String rua;
    private String complemento;

    public RetornoCEP(Long codigoCidade, Long codigoBairro, String rua, String complemento) {
        this.codigoCidade = codigoCidade;
        this.codigoBairro = codigoBairro;
        this.rua = rua;
        this.complemento = complemento;
    }

    public Long getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(Long codigoCidade) {
        this.codigoCidade = codigoCidade;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
