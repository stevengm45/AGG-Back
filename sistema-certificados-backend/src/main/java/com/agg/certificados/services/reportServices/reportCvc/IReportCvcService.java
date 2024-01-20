package com.agg.certificados.services.reportServices.reportCvc;

import com.agg.certificados.dtos.request.ReportCvcRequestDto;
import com.agg.certificados.dtos.response.ReportResponseDto;

public interface IReportCvcService {
    ReportResponseDto setData(ReportCvcRequestDto dto);

}