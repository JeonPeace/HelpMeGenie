package com.jeonpeace.helpmegenie.book;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeonpeace.helpmegenie.api.service.AladinApiService;
import com.jeonpeace.helpmegenie.book.domain.BookLookUpDto;
import com.jeonpeace.helpmegenie.book.domain.BookSearchDto;

@Controller
@RequestMapping("/book")
public class BookController {

    private final AladinApiService aladinApiService;

    public BookController(AladinApiService aladinApiService) {
        this.aladinApiService = aladinApiService;
    }	
	
	@GetMapping("/search-view")
	public String searchView(@RequestParam(value="searchKeyword", required=false) String searchKeyword, Model model) {
		
		if(searchKeyword != null) {
			List<BookSearchDto> searchBookList = aladinApiService.searchBooksByTitle(searchKeyword);
			
			model.addAttribute("searchBooks", searchBookList);
		}
		
		List<BookSearchDto> recommendBookList = aladinApiService.getRecommendBooks();

		model.addAttribute("recommendBooks", recommendBookList);
		
		List<BookLookUpDto> lookUpBook = aladinApiService.getBookByIsbn("9788932917245");
		
		model.addAttribute("book", lookUpBook);
		
		return "/book/search";
	}
	
}
