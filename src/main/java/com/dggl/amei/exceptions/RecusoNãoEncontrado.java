package com.dggl.amei.exceptions;

public class RecusoNãoEncontrado extends RuntimeException{

    public RecusoNãoEncontrado(Object id){
        super("Recurso não encontrado: id" + id);
    }
}
