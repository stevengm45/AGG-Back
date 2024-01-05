package com.agg.certificados.dtos.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TypeRcdResponseDto {
    public Long id_type_rcd;
    public String name;
    public String description;
    public Boolean status;
}
