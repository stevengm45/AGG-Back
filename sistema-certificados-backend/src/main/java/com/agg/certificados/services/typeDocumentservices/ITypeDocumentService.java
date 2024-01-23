package com.agg.certificados.services.typeDocumentservices;

import com.agg.certificados.dtos.response.TypeDocumentResponseDto;

import java.util.List;

public interface ITypeDocumentService {
    List<TypeDocumentResponseDto> getActive();
}
