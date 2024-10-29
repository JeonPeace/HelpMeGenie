package com.jeonpeace.helpmegenie.report.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Gallery {

	private int reportId;
	private int userId;
	private String userLoginId;
	
	private String cover;
	private String author;
	private String title;
	
	private String contents;
	
	private int likeCount;
	private boolean loginUserLike;

	private int commentCount;
	
}
