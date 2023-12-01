package com.example.be.service;

import com.example.be.dto.ReportDto;
import com.example.be.entity.Report;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProgressService {

    public int insertReport(Report report);

    public int updateReport(Report report);

    public int insertReportDto(ReportDto reportDto);

    public int updateReportDto(ReportDto reportDto);

    public ReportDto selectReportDtoById(Long id);

}
