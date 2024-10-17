package com.jeonpeace.helpmegenie.book.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LookUpResponseDto {

	private List<BookLookUpDto> item;
	
}
