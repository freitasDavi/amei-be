package com.dggl.amei.exceptions;

public class FaturamentoExcedido extends RuntimeException{

    public FaturamentoExcedido() {
        super("Seu faturamento excedeu o valor do seu plano, por favor faça o upgrade");
    }
}
