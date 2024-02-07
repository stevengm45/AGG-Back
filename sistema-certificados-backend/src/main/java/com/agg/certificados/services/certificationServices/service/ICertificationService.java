package com.agg.certificados.services.certificationServices.service;

import com.agg.certificados.dtos.response.BandejaCertificacionesResponseDto;
import com.agg.certificados.dtos.response.DataGeneratorResponseDto;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

public interface ICertificationService {
    String generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName);
    FileBase64ResponseDto generateCertificates(DataGeneratorResponseDto dto,boolean isNew, Long consecutive);
    List<BandejaCertificacionesResponseDto> getCertifications(@Nullable String create_date,
                                                              @Nullable String number_certification,
                                                              @Nullable String number_id);

    }
