package com.agg.certificados.controllers;

import com.agg.certificados.dtos.request.ReportCvcRequestDto;
import com.agg.certificados.services.reportServices.reportCvc.IReportCvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private IReportCvcService reportCvcService;

    @GetMapping()
    public ResponseEntity<String> getReportCvc(@RequestBody ReportCvcRequestDto dto){

        return new ResponseEntity<>(reportCvcService.setData(dto), HttpStatus.OK);
    }
}
