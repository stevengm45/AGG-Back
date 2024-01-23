package com.agg.certificados.utils;

public enum TypeRcdEnum {
    Uno(1L),
    UnoUno(2L),
    UnoDos(3L),
    UnoTres(4L),
    UnoCuatro(5L),
    Dos(6L),
    DosUno(7L),
    DosDos(8L),
    DosTres(9L);

    private final Long numero;

    TypeRcdEnum(Long numero) {
        this.numero = numero;
    }
    public Long getNumberId(){
        return numero;
    }

}
