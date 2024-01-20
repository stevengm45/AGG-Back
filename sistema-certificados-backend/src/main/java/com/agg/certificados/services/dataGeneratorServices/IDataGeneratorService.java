package com.agg.certificados.services.dataGeneratorServices;

import com.agg.certificados.dtos.request.DataGeneratorRequestDto;
import com.agg.certificados.dtos.response.DataGeneratorResponseDto;

public interface IDataGeneratorService {
    Long save(DataGeneratorRequestDto dto);
    DataGeneratorResponseDto getInformationCertificate(Long idDataGenerator);
    DataGeneratorResponseDto getInformationGetCertificate(Long idDataGenerator);
}
