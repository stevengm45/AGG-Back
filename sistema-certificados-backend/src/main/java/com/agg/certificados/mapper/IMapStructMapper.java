package com.agg.certificados.mapper;

import com.agg.certificados.dtos.request.QuantitiesRcdRequestDto;
import com.agg.certificados.dtos.response.*;
import com.agg.certificados.entity.*;

public interface IMapStructMapper {
    QuantitiesRcd QuantitiesRcdRequestDtoToQuantitiesRcd(QuantitiesRcdRequestDto dto, DataGenerator dataGenerator);
    DataGeneratorResponseDto DataGeneratorToDataGeneratorResponseDto(DataGenerator entity);
    DataManagerResponseDto DataManagerToDataManagerResponseDto(DataManager entity);
    ManagerResponseDto ManagerToManagerResponseDto(Manager entity);
    TypeDocumentResponseDto TypeDocumentToTypeDocumentResponseDto(TypeDocument entity);
    DataDriverResponseDto DataDriverToDataDriverResponseDto(DataDriver entity);
    QuantitiesRcdResponseDto QuantitiesRcdToQuantitiesRcdResponseDto(QuantitiesRcd entity);
    TypeRcdResponseDto TypeRcdToTypeRcdResponseDto(TypeRcd entity);
    CertificationMiniResponseDto CertificationToCertificationMiniResponseDto(Certification certification);
}
