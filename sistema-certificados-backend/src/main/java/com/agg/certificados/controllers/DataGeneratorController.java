package com.agg.certificados.controllers;

import com.agg.certificados.dtos.request.BotaderoRequestDto;
import com.agg.certificados.dtos.request.DataGeneratorRequestDto;
import com.agg.certificados.dtos.response.DataGeneratorResponseDto;
import com.agg.certificados.services.dataGeneratorServices.IDataGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generator")
public class DataGeneratorController {
    @Autowired
    private IDataGeneratorService dataGeneratorService;

    @PostMapping
    public ResponseEntity<Long> saveDataGenerator(@RequestBody DataGeneratorRequestDto dto) {

        return ResponseEntity.ok(dataGeneratorService.save(dto));
    }
    @GetMapping("/{idDataGenerator}")
    public DataGeneratorResponseDto getById(@PathVariable("idDataGenerator") Long idDataGenerator){
        return dataGeneratorService.getInformationCertificate(idDataGenerator);
    }
}
