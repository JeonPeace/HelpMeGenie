package com.jeonpeace.helpmegenie.book.domain;

import java.util.Map;

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
public class BookLookUpDto {

    private String title;
    private String author;
    private String description;
    private String cover;
	
    private Map<String, Object> subInfo;
	
}
