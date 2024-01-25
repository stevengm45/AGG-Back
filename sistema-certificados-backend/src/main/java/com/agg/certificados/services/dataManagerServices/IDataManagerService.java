package com.agg.certificados.services.dataManagerServices;

import com.agg.certificados.dtos.response.DataManagerResponseDto;

import java.util.List;

public interface IDataManagerService {
    List<DataManagerResponseDto> getAll();
    DataManagerResponseDto getbyId(Long id);
}
