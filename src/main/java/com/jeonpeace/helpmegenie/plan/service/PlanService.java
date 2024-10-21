package com.jeonpeace.helpmegenie.plan.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.repository.PlanRepository;

@Service
public class PlanService {

	private PlanRepository planRepository;
	
	public PlanService(PlanRepository planRepository) {
		this.planRepository = planRepository;
	}
	
	public Plan addPlan(int userId
						, String isbn13
						, LocalDate startDate
						, LocalDate endDate
						, int totalPage) {
		
		Plan plan = Plan.builder()
						.userId(userId)
						.isbn13(isbn13)
						.startDate(startDate)
						.endDate(endDate)
						.totalPage(totalPage)
						.donePage(0)
						.finished(false)
						.build();
		
		Plan result = planRepository.save(plan);
		
		return result;
	}

	public Plan getPlan(int userid) {
		
		Plan plan = planRepository.findByUserId(userid);
		
		return plan;
	}
	
}
