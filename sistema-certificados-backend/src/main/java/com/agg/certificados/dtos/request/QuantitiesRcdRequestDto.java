package com.agg.certificados.dtos.request;

import com.agg.certificados.entity.DataGenerator;
import com.agg.certificados.entity.TypeRcd;
import jakarta.persistence.*;

public class QuantitiesRcdRequestDto {
    public Long type_rcd_id;
    public Long quantity_rcd;
}
