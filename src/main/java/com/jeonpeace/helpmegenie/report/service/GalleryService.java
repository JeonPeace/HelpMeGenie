package com.jeonpeace.helpmegenie.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.repository.PlanRepository;
import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.dto.Gallery;
import com.jeonpeace.helpmegenie.report.repository.ReportRepository;

@Service
public class GalleryService {

	private ReportRepository reportRepository;
	private PlanRepository planRepository;
	
	public GalleryService(ReportRepository reportRepository, PlanRepository planRepository) {
		this.reportRepository = reportRepository;
		this.planRepository = planRepository;
	}
	
	public List<Gallery> getReportList(){
		
		List<Report> reportList = reportRepository.findTop5ByOrderByIdDesc();
		
		List<Gallery> galleryList = new ArrayList<>();
		
		for(Report report:reportList) {
			
			int planId = report.getPlanId();
			
			Plan plan = planRepository.findById(planId);
			
			Gallery gallery = Gallery.builder()
									 .reportId(report.getId())
									 .userId(report.getUserId())
									 .contents(report.getContents())
									 .cover(plan.getCover())
									 .title(plan.getTitle())
									 .author(plan.getAuthor())
									 .build();
			
			
			galleryList.add(gallery);
		}
		
		return galleryList;
	}
	
}
