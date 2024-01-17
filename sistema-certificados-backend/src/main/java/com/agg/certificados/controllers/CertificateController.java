package com.agg.certificados.controllers;


import com.agg.certificados.dtos.response.BandejaCertificacionesResponseDto;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.agg.certificados.services.certificationServices.service.ICertificationService;
import com.agg.certificados.services.dataGeneratorServices.IDataGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

import java.util.List;


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
    @GetMapping()
    public List<BandejaCertificacionesResponseDto> getCertifications(@RequestParam(required = false) String create_date,
                                                                     @RequestParam(required = false) String number_certification,
                                                                     @RequestParam(required = false) String number_id ){

        return certificateService.getCertifications(create_date,number_certification,number_id);
    }

}
