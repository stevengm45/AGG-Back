package com.agg.certificados.dtos.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataDriverRequestDto {
    @JsonIgnore
    public Long data_generator_id;
    public String name;
    public Long type_document_id;
    public String number_id;
    public String vehicle_plate;
}
