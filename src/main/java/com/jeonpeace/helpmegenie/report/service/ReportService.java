package com.jeonpeace.helpmegenie.report.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.report.domain.Comment;
import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.repository.CommentRepository;
import com.jeonpeace.helpmegenie.report.repository.ReportRepository;
import com.jeonpeace.helpmegenie.user.domain.User;
import com.jeonpeace.helpmegenie.user.service.UserService;

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
	
	public Report getReport(int reportId) {
		
		Optional<Report> optionalReport = reportRepository.findById(reportId);
		
		Report report = optionalReport.orElse(null);
		
		return report;
	}

	
}
