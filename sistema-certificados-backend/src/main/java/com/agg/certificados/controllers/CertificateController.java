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

    @GetMapping
    public String pruebaCertifacte() {


        Map<String, Object> data = new HashMap<>();
        Botadero botadero = new Botadero();
        botadero.setStatus(true);
        botadero.setCity("Ciudad Tolima");
        botadero.setProperty_name("Property Ibagué");
        botadero.setId_botadero(1);

        data.put("botadero", botadero);

        List<Rol> roles = new ArrayList<>();
        Rol rol1 = new Rol();

        rol1.setName("Admin");
        rol1.setRolId(1L);

        Rol rol2 = new Rol();

        rol2.setName("Prueba jeje");
        rol2.setRolId(2L);

        roles.add(rol1);
        roles.add(rol2);

        data.put("roles", roles);

        //-------------
        TypeDocument typeDocument = new TypeDocument(2,"Prueba de tipo documento",true,"TPD");

        DataDriver dataDriver = new DataDriver();
        dataDriver.type_document_id = typeDocument;

        data.put("dataDriver", dataDriver);


        //Se debe cambiar el nombre del TemplateName, para generar el archivo, ya que de acuerdo a eso asi mismo se genera

        return certificateService.generatePdfFile("prueba",data,"prueba.pdf");

    }
    @GetMapping("/{id}")
    public FileBase64ResponseDto generateCertificates(@PathVariable("id") Long idDataGenerator){

        return certificateService.generateCertificates(dataGeneratorService.getInformationCertificate(idDataGenerator));
    }
}
