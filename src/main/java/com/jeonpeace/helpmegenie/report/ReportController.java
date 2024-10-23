package com.jeonpeace.helpmegenie.report;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeonpeace.helpmegenie.api.service.AladinApiService;
import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.service.PlanService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/report")
public class ReportController {

	private PlanService planService;


	public ReportController(PlanService planService, AladinApiService aladinApiService) {
		this.planService = planService;
	}
	
	
	@GetMapping("/choose-view")
	public String chooseView(HttpSession session, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Plan> planList = planService.getPlanFinished(userId);

		
		model.addAttribute("planList", planList);
		
		return "/report/choose";
	}
	
	@GetMapping("/create-view")
	public String createView() {
		
		return "/report/create";
	}
	
}
