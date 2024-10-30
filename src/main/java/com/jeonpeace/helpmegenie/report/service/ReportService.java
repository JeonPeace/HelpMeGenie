package com.jeonpeace.helpmegenie.report.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.image.service.ImageService;
import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.repository.ReportRepository;

@Service
public class ReportService {

	private ReportRepository reportRepository;

	
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	public Report addTempReport(int userId, int planId, String contents) {
		
		Report report = Report.builder()
							  .userId(userId)
							  .planId(planId)
							  .contents(contents)
							  .build();
		
		Report result = reportRepository.save(report);
		
		return result;
	}
	
	public Report addRealReport(Report tempReport) {
		
		String tempContents = tempReport.getContents();
		
		tempContents.replace("/imagesTemp", "/imagesContents");
		
		tempReport.setContents(tempContents);
		
		return tempReport;
	}
	
	public Report getReport(int reportId) {
		
		Optional<Report> optionalReport = reportRepository.findById(reportId);
		
		Report report = optionalReport.orElse(null);
		
		return report;
	}

	
}
