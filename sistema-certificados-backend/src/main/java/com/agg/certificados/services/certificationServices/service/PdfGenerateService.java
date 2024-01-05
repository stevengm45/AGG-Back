package com.agg.certificados.services.certificationServices.service;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.lowagie.text.DocumentException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

import java.util.Base64;
@Service
public class PdfGenerateService implements IPdfGenerateService{
    private Logger logger = LoggerFactory.getLogger(PdfGenerateService.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public FileBase64ResponseDto generatePdfFile(String templateName, Map<String, Object> data, String pdfFileName) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process(templateName, context);
        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();

            // Convertir el contenido del ByteArrayOutputStream a Base64
            byte[] pdfBytes = outputStream.toByteArray();

            String base64String = Base64.getEncoder().encodeToString(pdfBytes);

            FileBase64ResponseDto dto = new FileBase64ResponseDto();

            dto.file = base64String;

            return dto;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
