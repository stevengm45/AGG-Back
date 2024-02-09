package com.agg.certificados.dtos.response;

import java.util.List;

public class DataGeneratorResponseDto {
    public Long id_data_generator;
    public BotaderoResponseDto botadero;
    public DataManagerResponseDto data_manager;
    public List<ManagerResponseDto> manager;
    public String unic_number;
    public String name;
    public TypeDocumentResponseDto type_document;
    public String number_id;
    public String legal_representative;
    public String address;
    public Long phone_number;
    public String email;
    public String address_rcd;
    public String reception_date_rcd;
    public TypeWeightResponseDto type_weight;
    public double total_rcd;
    public List<QuantitiesRcdResponseDto> quantitiesRcd;
    public DataDriverResponseDto data_driver;
    public CertificationMiniResponseDto certification;
}
