package com.dggl.amei.exceptions;

public class RecursoNaoEncontrado extends RuntimeException{

    public RecursoNaoEncontrado(String nome, Object id){
        super("Não foi encontrado " + nome + " com Id" + id);
    }
}
