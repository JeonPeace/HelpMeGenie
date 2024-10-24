package com.jeonpeace.helpmegenie.report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.dto.Gallery;
import com.jeonpeace.helpmegenie.report.repository.ReportRepository;

@Service
public class GalleryService {

	private ReportRepository reportRepository;
	
	public GalleryService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
	
	public List<Gallery> getReportList(){
		
		List<Report> reportList = reportRepository.findTop5ByOrderByIdDesc();
		
		List<Gallery> galleryList = new ArrayList<>();
		
		for(Report report:reportList) {
			
			Gallery gallery = Gallery.builder()
									 .reportId(report.getId())
									 .userId(report.getUserId())
									 .contents(report.getContents())
									 .build();
			
			
			galleryList.add(gallery);
		}
		
		return galleryList;
	}
	
}
