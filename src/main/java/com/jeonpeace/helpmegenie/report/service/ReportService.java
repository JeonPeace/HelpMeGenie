package com.jeonpeace.helpmegenie.report.service;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.repository.ReportRepository;

@Service
public class ReportService {

	private ReportRepository reportRepository;
	
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	public Report addReport(int userId, int planId, String contents) {
		
		Report report = Report.builder()
							  .userId(userId)
							  .planId(planId)
							  .contents(contents)
							  .build();
		
		Report result = reportRepository.save(report);
		
		return result;
	}
	
}
