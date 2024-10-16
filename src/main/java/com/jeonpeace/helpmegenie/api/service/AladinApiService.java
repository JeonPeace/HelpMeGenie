package com.jeonpeace.helpmegenie.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.api.repository.AladinApiRepository;
import com.jeonpeace.helpmegenie.book.domain.BookSearchDto;
import com.jeonpeace.helpmegenie.book.domain.SearchResponseDto;

@Service
public class AladinApiService {

    private AladinApiRepository aladinApiRepository;

    public AladinApiService(AladinApiRepository aladinApiRepository) {
        this.aladinApiRepository = aladinApiRepository;
    }

    public List<BookSearchDto> searchBooksByTitle(String searchKeyword) {

    	SearchResponseDto searchResult = aladinApiRepository.searchBooksByTitle(searchKeyword).block();
    	
    	if(searchResult.getItem() != null) {
    		List<BookSearchDto> bookList = searchResult.getItem();
        	
        	return bookList;
    	}else {
    		List<BookSearchDto> bookList = null;
        	
        	return bookList;
    	}
    }

    public List<BookSearchDto> getRecommendBooks(){
    	
    	SearchResponseDto recommendResult = aladinApiRepository.getRecommendedBooks().block();
    	
    	List<BookSearchDto> bookList = recommendResult.getItem();
    	
    	return bookList;
    	
    }
    
}
