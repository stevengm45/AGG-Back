package com.agg.certificados.services.certificationServices.service;

import com.agg.certificados.dtos.response.DataGeneratorResponseDto;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;

import java.util.Map;

public interface ICertificationService {
    String generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName);
    FileBase64ResponseDto generateCertificates(DataGeneratorResponseDto dto);

}
