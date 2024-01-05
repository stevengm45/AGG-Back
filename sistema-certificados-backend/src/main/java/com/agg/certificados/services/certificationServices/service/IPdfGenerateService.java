package com.agg.certificados.services.certificationServices.service;

import com.agg.certificados.dtos.response.FileBase64ResponseDto;

import java.util.Map;

public interface IPdfGenerateService {
    FileBase64ResponseDto generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName);

}
