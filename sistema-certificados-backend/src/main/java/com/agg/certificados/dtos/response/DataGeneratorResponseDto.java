package com.agg.certificados.dtos.response;

import com.agg.certificados.dtos.request.DataDriverRequestDto;
import com.agg.certificados.dtos.request.QuantitiesRcdRequestDto;
import javax.validation.constraints.NotNull;

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
    public Long total_rcd;
    public List<QuantitiesRcdResponseDto> quantitiesRcd;
    public DataDriverResponseDto data_driver;
    public PriceRcdResponseDto price_rcd;
}
