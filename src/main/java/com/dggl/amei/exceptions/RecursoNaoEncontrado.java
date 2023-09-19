package com.dggl.amei.exceptions;

public class RecursoNaoEncontrado extends RuntimeException{

    public RecursoNaoEncontrado(Object id){
        super("Recurso não encontrado: id" + id);
    }
}
