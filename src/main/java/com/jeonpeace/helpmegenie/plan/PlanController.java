package com.jeonpeace.helpmegenie.plan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

	@GetMapping("/test-view")
	public String calendarTest() {
		
		return "/test";
	}
	
}
