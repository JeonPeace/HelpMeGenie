package com.jeonpeace.helpmegenie.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class AladinApiService {

    private final String BASE_URL = "http://www.aladin.co.kr/ttb/api/";
    private final String TTB_KEY = "ttbjeonpeace05222134001";

    private final WebClient webClient;

    public AladinApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    // 책 제목으로 검색 (ItemSearch)
    public Mono<String> searchBooksByTitle(String query) {
        String ITEM_SEARCH_URL = "ItemSearch.aspx?ttbkey=" + TTB_KEY + "&Query=" + query 
            + "&QueryType=Title&MaxResults=4&start=1&SearchTarget=Book&output=js&Version=20131101&Cover=big";
        
        return webClient.get()
                .uri(ITEM_SEARCH_URL)
                .retrieve()
                .bodyToMono(String.class);
    }

    // ISBN으로 책 정보 조회 (ItemLookUp)
    public Mono<String> lookupBookByISBN(String isbn) {
        String ITEM_LOOK_UP_URL = "ItemLookUp.aspx?ttbkey=" + TTB_KEY + "&itemIdType=ISBN&ItemId=" + isbn 
            + "&output=js&Version=20131101&Cover=big";
        
        return webClient.get()
                .uri(ITEM_LOOK_UP_URL)
                .retrieve()
                .bodyToMono(String.class);
    }

    // 추천 도서 목록 (ItemList)
    public Mono<String> getRecommendedBooks() {
        String ITEM_RECOMMEND_URL = "ItemList.aspx?ttbkey=" + TTB_KEY 
            + "&QueryType=ItemNewSpecial&MaxResults=4&start=1&SearchTarget=Book&output=js&Version=20131101&Cover=big";
        
        return webClient.get()
                .uri(ITEM_RECOMMEND_URL)
                .retrieve()
                .bodyToMono(String.class);
    }
}