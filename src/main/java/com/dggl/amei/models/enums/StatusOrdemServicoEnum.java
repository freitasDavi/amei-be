package com.dggl.amei.models.enums;

public enum StatusOrdemServicoEnum {

    AGUARDANDO_EMISSAO(1),
    EMITIDA(2);
    private int codigoEnum;

    StatusOrdemServicoEnum(int codigoEnum) {
        this.codigoEnum = codigoEnum;
    }

    public int getCodigoEnum() {
        return codigoEnum;
    }

    public static StatusOrdemServicoEnum valueOf(int codigoEnum) {
        for (StatusOrdemServicoEnum value : StatusOrdemServicoEnum.values()) {
            if (value.getCodigoEnum() == codigoEnum) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de Enum Inválido");
    }
}
