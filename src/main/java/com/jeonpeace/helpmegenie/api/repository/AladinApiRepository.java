package com.jeonpeace.helpmegenie.api.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.jeonpeace.helpmegenie.book.domain.LookUpResponseDto;
import com.jeonpeace.helpmegenie.book.domain.SearchResponseDto;

import reactor.core.publisher.Mono;

@Repository
public class AladinApiRepository {

    private final String BASE_URL = "http://www.aladin.co.kr/ttb/api/";
    private final String TTB_KEY = "ttbjeonpeace05222134001";

    private final WebClient webClient;

    public AladinApiRepository(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }	
	
    // 책 제목으로 검색 (ItemSearch)
    public Mono<SearchResponseDto> searchBooksByTitle(String query) {
        String ITEM_SEARCH_URL = "ItemSearch.aspx?ttbkey=" + TTB_KEY + "&Query=" + query 
            + "&QueryType=Keyword&MaxResults=4&start=1&SearchTarget=Book&output=js&Version=20131101&Cover=Big";
        
        return webClient.get()
                .uri(ITEM_SEARCH_URL)
                .retrieve()
                .bodyToMono(SearchResponseDto.class);  // SearchResponseDto로 파싱
    }    
 
    // ISBN으로 책 정보 조회 (ItemLookUp)
    public Mono<LookUpResponseDto> getLookupBookByISBN(String isbn) {
        String ITEM_LOOK_UP_URL = "ItemLookUp.aspx?ttbkey=" + TTB_KEY + "&itemIdType=ISBN&ItemId=" + isbn 
            + "&output=js&Version=20131101&Cover=MidBig";
        
        return webClient.get()
                .uri(ITEM_LOOK_UP_URL)
                .retrieve()
                .bodyToMono(LookUpResponseDto.class);
    }

    // 추천 도서 목록 (ItemList)
    public Mono<SearchResponseDto> getRecommendedBooks() {
        String ITEM_RECOMMEND_URL = "ItemList.aspx?ttbkey=" + TTB_KEY 
            + "&QueryType=Bestseller&MaxResults=4&start=1&SearchTarget=Book&output=js&Version=20131101&Cover=Big";
        
        return webClient.get()
                .uri(ITEM_RECOMMEND_URL)
                .retrieve()
                .bodyToMono(SearchResponseDto.class);
    }    
    
    
}
