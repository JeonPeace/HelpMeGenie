package com.jeonpeace.helpmegenie.plan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Plan getPlanUnFinished(int userId) {
		
		Plan plan = planRepository.findByUserIdAndFinished(userId, false);
		
		return plan;
	}
	
	public String getIsbn13(int planId) {
		
		String isbn13 = planRepository.getIsbn13ById(planId);
		
		return isbn13;
	}
	
	@Transactional
	public List<Integer> addPage(int planId, int addPage) {
		
		Plan plan = planRepository.findById(planId);
		
		List<Integer> pageList = new ArrayList<>();
		
		int donePage = plan.getDonePage();
		pageList.add(donePage);
		
		donePage = donePage + addPage;
		pageList.add(donePage);
		
		plan.setDonePage(donePage);
		
		return pageList;
	}
	
	@Transactional
	public boolean endPlan(int planId) {
		
		Plan plan = planRepository.findById(planId);
		
		plan.setFinished(true);
		
		return plan.isFinished();
	}
	
	@Transactional
	public String deletePlan(int planId) {
		
		Plan plan = planRepository.findById(planId);
		
		if(plan != null) {
			planRepository.delete(plan);
			return "success";
		}else {
			return "fail";
		}
		
	}
	
}
