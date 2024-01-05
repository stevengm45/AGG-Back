package com.agg.certificados.dtos.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ManagerResponseDto {
    public Long id_manager;
    public String name;
    public Boolean status;
}
