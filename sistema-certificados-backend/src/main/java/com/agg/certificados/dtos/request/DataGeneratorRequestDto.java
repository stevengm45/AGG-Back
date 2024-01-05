package com.agg.certificados.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class DataGeneratorRequestDto {
    @NotNull
    public int botadero_id;
    @NotNull
    public Long data_manager_id;
    @NotNull
    public Long manager_id;
    public String unic_number;
    @NotNull
    public String name;
    @NotNull
    public Long type_document_id;
    @NotNull
    public String number_id;
    @NotNull
    public String legal_representative;
    @NotNull
    public String address;
    @NotNull
    public Long phone_number;
    @NotNull
    public String email;
    @NotNull
    public String address_rcd;
    @NotNull
    public String reception_date_rcd;
    @NotNull
    public List<QuantitiesRcdRequestDto> quantitiesRcd;
    @NotNull
    public DataDriverRequestDto data_driver;
}
