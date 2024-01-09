package com.agg.certificados.dtos.request;

import com.agg.certificados.entity.DataGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PriceRcdRequestDto {
    public Double price_m3;
    @JsonIgnore
    public Double total_price;
}
