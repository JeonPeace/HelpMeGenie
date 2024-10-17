package com.jeonpeace.helpmegenie.plan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan")
public class PlanController {

	@GetMapping("/test-view")
	public String calendarTest() {
		
		return "/test";
	}
	
	@GetMapping("/progress-view")
	public String progressView() {
		
		return "/plan/progress";
	}
	
	@GetMapping("/new-plan-view")
	public String createPlan() {
		
		return "/plan/create";
	}
	
}
