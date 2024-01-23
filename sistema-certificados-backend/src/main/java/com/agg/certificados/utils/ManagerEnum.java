package com.agg.certificados.utils;

public enum ManagerEnum {
    PuntoLimpio(1L),
    Aprovechamiento(2L),
    DisposicionFinal(3L);
    private final Long numero;

    ManagerEnum(Long numero) {
        this.numero = numero;
    }
    public Long getNumberId(){
        return numero;
    }
}
