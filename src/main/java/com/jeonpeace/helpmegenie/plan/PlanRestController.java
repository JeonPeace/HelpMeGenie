package com.jeonpeace.helpmegenie.plan;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.service.PlanService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/plan")
public class PlanRestController {

	private PlanService planService;
	
	public PlanRestController(PlanService planService) {
		this.planService = planService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createPlan(@RequestParam("isbn13") String isbn13
										, @RequestParam("startDate") LocalDate startDate
										, @RequestParam("endDate") LocalDate endDate
										, @RequestParam("totalPage") int totalPage
										, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Plan plan = planService.addPlan(userId, isbn13, startDate, endDate, totalPage);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(plan != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;	
	}
}
