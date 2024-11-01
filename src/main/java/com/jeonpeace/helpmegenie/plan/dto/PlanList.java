package com.jeonpeace.helpmegenie.plan.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlanList {

	private int planId;
	
	private String cover;
	private String author;
	private String title;
	private int totalPage;
	private int donePage;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
}
