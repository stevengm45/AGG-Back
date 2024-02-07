package com.agg.certificados.utils;

public enum TypeWeightEnum {
    Toneladas(1L),
    Kilos(2L);
    private final Long numero;

    TypeWeightEnum(Long numero) {
        this.numero = numero;
    }
    public Long getNumberId(){
        return numero;
    }
}
