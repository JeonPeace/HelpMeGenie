package com.jeonpeace.helpmegenie.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookSearchDto {

    private String title;
    private String author;
    private String isbn13;
    private String description;
    private String cover;	
	
}
