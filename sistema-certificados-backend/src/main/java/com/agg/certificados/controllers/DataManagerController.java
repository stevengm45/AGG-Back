package com.agg.certificados.controllers;

import com.agg.certificados.dtos.response.BotaderoResponseDto;
import com.agg.certificados.dtos.response.DataManagerResponseDto;
import com.agg.certificados.services.dataManagerServices.IDataManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data-manager")
public class DataManagerController {
    @Autowired
    private IDataManagerService dataManagerService;

    @GetMapping
    public ResponseEntity<List<DataManagerResponseDto>> getAll() {
        return ResponseEntity.ok(dataManagerService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataManagerResponseDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(dataManagerService.getbyId(id));
    }
}
