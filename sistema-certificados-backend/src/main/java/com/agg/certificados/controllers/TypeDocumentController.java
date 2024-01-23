package com.agg.certificados.controllers;

import com.agg.certificados.dtos.response.BotaderoResponseDto;
import com.agg.certificados.dtos.response.TypeDocumentResponseDto;
import com.agg.certificados.services.typeDocumentservices.ITypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/type-document")

public class TypeDocumentController {
    @Autowired
    private ITypeDocumentService typeDocumentService;
    @GetMapping("/active")
    public ResponseEntity<List<TypeDocumentResponseDto>> getAllActiveBotaderos() {
        return ResponseEntity.ok(typeDocumentService.getActive());
    }
}
