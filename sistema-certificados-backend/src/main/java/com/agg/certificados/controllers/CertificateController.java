package com.agg.certificados.controllers;


import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.agg.certificados.services.certificationServices.service.ICertificationService;
import com.agg.certificados.services.dataGeneratorServices.IDataGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;


@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    private ICertificationService certificateService;
    @Autowired
    private IDataGeneratorService dataGeneratorService;
    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/{id}")
    public FileBase64ResponseDto generateCertificates(@PathVariable("id") Long idDataGenerator){

        return certificateService.generateCertificates(dataGeneratorService.getInformationCertificate(idDataGenerator));
    }


}
