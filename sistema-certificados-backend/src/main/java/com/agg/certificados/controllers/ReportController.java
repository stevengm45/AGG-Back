package com.agg.certificados.controllers;

import com.agg.certificados.dtos.request.ReportCvcRequestDto;
import com.agg.certificados.dtos.response.ReportResponseDto;
import com.agg.certificados.services.reportServices.reportCvc.IReportCvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private IReportCvcService reportCvcService;

    @PostMapping()
    public ResponseEntity<ReportResponseDto> getReportCvc(@RequestBody ReportCvcRequestDto dto){

        return new ResponseEntity<>(reportCvcService.setData(dto), HttpStatus.OK);
    }
}
