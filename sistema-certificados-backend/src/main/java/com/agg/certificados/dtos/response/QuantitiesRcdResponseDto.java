package com.agg.certificados.dtos.response;

import com.agg.certificados.entity.DataGenerator;
import com.agg.certificados.entity.TypeRcd;
import jakarta.persistence.*;

public class QuantitiesRcdResponseDto {
    public Long id_quantities_rcd;
    public TypeRcdResponseDto type_rcd;
    public Long data_generator_id;
    public Long quantity_rcd;
}
