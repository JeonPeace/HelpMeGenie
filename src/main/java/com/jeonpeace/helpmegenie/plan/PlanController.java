package com.jeonpeace.helpmegenie.plan;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeonpeace.helpmegenie.api.service.AladinApiService;
import com.jeonpeace.helpmegenie.book.domain.BookLookUpDto;

@Controller
@RequestMapping("/plan")
public class PlanController {

	private AladinApiService aladinApiService;
	
	public PlanController(AladinApiService aladinApiService) {
		this.aladinApiService = aladinApiService;
	}
	
	@GetMapping("/test-view")
	public String calendarTest() {
		
		return "/test";
	}
	
	@GetMapping("/progress-view")
	public String progressView(Model model) {
		
		
		
		List<BookLookUpDto> lookUpBook = aladinApiService.getBookByIsbn("9788932917245");
		
		model.addAttribute("book", lookUpBook);
		
		return "/plan/progress";
	}
	
	@GetMapping("/new-plan-view")
	public String createPlan(@RequestParam(value="isbn13", required=false) String isbn13, Model model) {
		
		List<BookLookUpDto> lookUpBook = aladinApiService.getBookByIsbn(isbn13);
		
		model.addAttribute("book", lookUpBook);
		
		return "/plan/create";
	}
	
}
