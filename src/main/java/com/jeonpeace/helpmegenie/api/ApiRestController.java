package com.jeonpeace.helpmegenie.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeonpeace.helpmegenie.api.service.AladinApiService;

import reactor.core.publisher.Mono;

@RestController
public class ApiRestController {

    private final AladinApiService aladinApiService;

    public ApiRestController(AladinApiService aladinApiService) {
        this.aladinApiService = aladinApiService;
    }

    // 책 제목으로 검색
    @GetMapping("/search")
    public Mono<String> searchBooks(@RequestParam("title") String title) {
        return aladinApiService.searchBooksByTitle(title);
    }

    // ISBN으로 책 조회
    @GetMapping("/lookup")
    public Mono<String> lookupBook(@RequestParam("isbn") String isbn) {
        return aladinApiService.lookupBookByISBN(isbn);
    }

    // 추천 도서 목록
    @GetMapping("/recommend")
    public Mono<String> getRecommendations() {
        return aladinApiService.getRecommendedBooks();
    }
	
}
