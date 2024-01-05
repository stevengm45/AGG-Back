package com.agg.certificados.dtos.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class TypeDocumentResponseDto {
    public int id_type_document;
    public String name;
    public String description;
    public boolean status;
}
