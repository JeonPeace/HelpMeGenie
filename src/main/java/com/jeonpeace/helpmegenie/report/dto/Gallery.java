package com.jeonpeace.helpmegenie.report.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Gallery {

	private int reportId;
	private int userId;
	private int userLoginId;
	
	private String cover;
	private String author;
	private String title;
	
	private String contents;
	
}
