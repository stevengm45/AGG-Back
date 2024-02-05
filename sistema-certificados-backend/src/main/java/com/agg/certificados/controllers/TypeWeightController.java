package com.agg.certificados.controllers;

import com.agg.certificados.dtos.response.TypeWeightResponseDto;
import com.agg.certificados.services.typeWeightService.ITypeWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/type-weight")

public class TypeWeightController {
    @Autowired
    private ITypeWeightService typeWeightService;
    @GetMapping("/active")
    public List<TypeWeightResponseDto> getall() throws Exception {

        return typeWeightService.getActive();

    }
}
