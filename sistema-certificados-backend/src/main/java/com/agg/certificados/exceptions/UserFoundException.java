package com.agg.certificados.exceptions;

public class UserFoundException extends Exception{

    public UserFoundException(){
        super("el usuario con ese username ya existe en la db");
    }

    public UserFoundException(String messages){
        super(messages);
    }
}
