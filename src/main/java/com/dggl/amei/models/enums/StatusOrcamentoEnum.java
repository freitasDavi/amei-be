package com.dggl.amei.models.enums;

public enum StatusOrcamentoEnum {

    ABERTO(1),
    APROVADO(2);
    private int codigoEnum;

    StatusOrcamentoEnum(int codigoEnum) {
        this.codigoEnum = codigoEnum;
    }

    public int getCodigoEnum() {
        return codigoEnum;
    }

    public static StatusOrcamentoEnum valueOf(int codigoEnum) {
        for (StatusOrcamentoEnum value : StatusOrcamentoEnum.values()) {
            if (value.getCodigoEnum() == codigoEnum) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de Enum Inválido");
    }
}
