package com.jeonpeace.helpmegenie.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeonpeace.helpmegenie.api.service.AladinApiService;
import com.jeonpeace.helpmegenie.book.domain.BookSearchDto;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    private final AladinApiService aladinApiService;

    public ApiRestController(AladinApiService aladinApiService) {
        this.aladinApiService = aladinApiService;
    }

    // 책 제목으로 검색
    @PostMapping("/search")
    public Map<String, Boolean> searchBooks(@RequestParam("searchKeyword") String searchKeyword, Model model) {
        
    	List<BookSearchDto> bookList = aladinApiService.searchBooksByTitle(searchKeyword);
    	
    	Map<String, Boolean> bookNotNull = new HashMap<>();
    	
    	if(bookList != null) {
        	model.addAttribute("books", bookList);
        	bookNotNull.put("bookNotNull", true);
        	return bookNotNull;
    	}else {
    		model.addAttribute("books", bookList);
    		bookNotNull.put("bookNotNull", false);
    		return bookNotNull;
    	}


    }
	
}
