package com.agg.certificados.controllers;


import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.agg.certificados.entity.Botadero;
import com.agg.certificados.entity.DataDriver;
import com.agg.certificados.entity.Rol;
import com.agg.certificados.entity.TypeDocument;
import com.agg.certificados.services.certificationServices.service.ICertificationService;
import com.agg.certificados.services.dataGeneratorServices.IDataGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/certificate")
public class CertificateController {
    @Autowired
    private ICertificationService certificateService;
    @Autowired
    private IDataGeneratorService dataGeneratorService;


    @GetMapping("/{id}")
    public FileBase64ResponseDto generateCertificates(@PathVariable("id") Long idDataGenerator){

        return certificateService.generateCertificates(dataGeneratorService.getInformationCertificate(idDataGenerator));
    }
}
