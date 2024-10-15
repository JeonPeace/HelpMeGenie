package com.jeonpeace.helpmegenie.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

	@GetMapping("/search-view")
	public String searchView() {
		
		return "/book/search";
	}
	
}
