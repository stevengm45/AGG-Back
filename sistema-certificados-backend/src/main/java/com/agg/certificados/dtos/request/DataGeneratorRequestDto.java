package com.agg.certificados.dtos.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DataGeneratorRequestDto {
    @NotNull
    public int botadero_id;
    @NotNull
    public Long data_manager_id;
    @NotNull
    public ManagerDataGeneratorRequestDto manager;
    public String unic_number;
    @NotNull
    @NotBlank
    public String name;
    @NotNull
    public Long type_document_id;
    @NotNull
    @NotBlank
    public String number_id;
    @NotNull
    @NotBlank
    public String legal_representative;
    @NotNull
    @NotBlank
    public String address;
    @NotNull
    public Long phone_number;
    @NotNull
    @NotBlank
    public String email;
    @NotNull
    @NotBlank
    public String address_rcd;
    @NotNull
    @NotBlank
    public String reception_date_rcd;
    @NotNull
    public QuantitiesTotal quantitiesRcd;
    @NotNull
    public DataDriverRequestDto data_driver;
}

