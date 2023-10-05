package com.dggl.amei.dtos.responses;

public class MessageWithBodyResponse<T> {

    private String message;
    private T object;

    public MessageWithBodyResponse (String message, T object) {
        this.message = message;
        this.object = object;
    }
}
