package com.agg.certificados.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("usuario no encontrado en la db");
    }

    public UserNotFoundException(String messages){
        super(messages);
    }
}
