package com.jeonpeace.helpmegenie.book.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponseDto {
    private List<BookSearchDto> item;  // item 배열 안에 각 책 정보가 들어 있음

}