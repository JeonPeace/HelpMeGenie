package com.jeonpeace.helpmegenie.report.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.image.service.ImageService;
import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.repository.CommentRepository;
import com.jeonpeace.helpmegenie.report.repository.LikeRepository;
import com.jeonpeace.helpmegenie.report.repository.ReportRepository;

@Service
public class ReportService {

	private ReportRepository reportRepository;
	private ImageService imageService;
	private LikeRepository likeRepository;
	private CommentRepository commentRepository;

	
	public ReportService(ReportRepository reportRepository, ImageService imageService, LikeRepository likeRepository, CommentRepository commentRepository) {
		this.reportRepository = reportRepository;
		this.imageService = imageService;
		this.likeRepository = likeRepository;
		this.commentRepository = commentRepository;
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
	
	public Report modifyReport(int reportId, String contents) {
		
		Optional<Report> optionalReport = reportRepository.findById(reportId);
		
		Report report = optionalReport.orElse(null);
		
		if(report != null) {
			
			Report modifyReport = report.toBuilder()
										.contents(contents)
										.build();
			
			return reportRepository.save(modifyReport);
		}else {
			return null;
		}	
	}
	
	public Report getReport(int reportId) {
		
		Optional<Report> optionalReport = reportRepository.findById(reportId);
		
		Report report = optionalReport.orElse(null);
		
		return report;
	}

	@Transactional
	public boolean deleteReport(int reportId) {	
		
		Optional<Report> optionalReport = reportRepository.findById(reportId);
		
		Report report = optionalReport.orElse(null);
		
		if(report != null) {
			imageService.deleteImageByReportId(reportId);
			likeRepository.deleteByReportId(reportId);
			commentRepository.deleteByReportId(reportId);
			reportRepository.deleteById(reportId);
			return true;
		}else {
			return false;
		}
	}	
	
}
