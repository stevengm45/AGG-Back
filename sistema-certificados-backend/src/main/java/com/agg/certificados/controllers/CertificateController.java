package com.agg.certificados.controllers;


import com.agg.certificados.dtos.response.BotaderoResponseDto;
import com.agg.certificados.dtos.response.FileBase64ResponseDto;
import com.agg.certificados.entity.Botadero;
import com.agg.certificados.entity.Rol;
import com.agg.certificados.services.certificationServices.service.IPdfGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private IPdfGenerateService pdfGenerateService;

    @GetMapping
    public FileBase64ResponseDto pruebaCertifacte() {


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

        //Se debe cambiar el nombre del TemplateName, para generar el archivo, ya que de acuerdo a eso asi mismo se genera

        return pdfGenerateService.generatePdfFile("prueba",data,"prueba.pdf");

    }
}
