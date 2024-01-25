package com.agg.certificados.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataDriverResponseDto {
    public Long id_data_driver;
    public Long data_generator_id;
    public String name;
    public TypeDocumentResponseDto type_document;
    public String number_id;
    public String vehicle_plate;
}
