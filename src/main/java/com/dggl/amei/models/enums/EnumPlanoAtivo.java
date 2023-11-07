package com.dggl.amei.models.enums;

public enum EnumPlanoAtivo {
    FREE(0),
    MEI(1),
    ME(2);

    private int codigoEnum;

    EnumPlanoAtivo(int codigoEnum) { this.codigoEnum = codigoEnum; }

    public int getPlanoAtivo () {
        return codigoEnum;
    }

    public static EnumPlanoAtivo valueOf(int codigoEnum) {
        for (EnumPlanoAtivo value : EnumPlanoAtivo.values()) {
            if (value.getPlanoAtivo() == codigoEnum) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de Enum Inválido");
    }
}
