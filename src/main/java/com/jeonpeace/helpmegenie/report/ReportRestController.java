package com.jeonpeace.helpmegenie.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.service.ReportService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/report")
public class ReportRestController {

	private ReportService reportService;
	
	public ReportRestController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createReport(@RequestParam("contents") String contents
											, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Report report = reportService.addReport(userId, contents);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(report != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
