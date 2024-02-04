package com.agg.certificados.services.typeWeightService;

import com.agg.certificados.dtos.response.TypeWeightResponseDto;

import java.util.List;

public interface ITypeWeightService {
    List<TypeWeightResponseDto> getActive();
}
