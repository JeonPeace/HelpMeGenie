package com.jeonpeace.helpmegenie.plan;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeonpeace.helpmegenie.api.service.AladinApiService;
import com.jeonpeace.helpmegenie.book.dto.BookLookUpDto;
import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.service.PlanService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/plan")
public class PlanController {

	private AladinApiService aladinApiService;
	private PlanService planService;
	
	public PlanController(AladinApiService aladinApiService, PlanService planService) {
		this.aladinApiService = aladinApiService;
		this.planService = planService;
	}
	
	@GetMapping("/test-view")
	public String calendarTest() {
		
		return "/test";
	}
	
	@GetMapping("/progress-view")
	public String progressView(HttpSession session
								, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Plan plan = planService.getPlanUnFinished(userId);
		
		if(plan != null) {
			String isbn13 = planService.getIsbn13(plan.getId());
			List<BookLookUpDto> lookUpBook = aladinApiService.getBookByIsbn(isbn13);
			model.addAttribute("book", lookUpBook);

			model.addAttribute("plan", plan);	
		}


		
		return "/plan/progress";
	}
	
	@GetMapping("/new-plan-view")
	public String createPlan(@RequestParam(value="isbn13", required=false) String isbn13, Model model) {
		
		List<BookLookUpDto> lookUpBook = aladinApiService.getBookByIsbn(isbn13);
		
		model.addAttribute("book", lookUpBook);
		
		return "/plan/create";
	}
	
}
