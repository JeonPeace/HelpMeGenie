package com.jeonpeace.helpmegenie.plan.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.api.service.AladinApiService;
import com.jeonpeace.helpmegenie.book.domain.BookLookUpDto;
import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.repository.PlanRepository;

@Service
public class PlanService {

	private PlanRepository planRepository;
	private AladinApiService aladinApiService;
	
	public PlanService(PlanRepository planRepository, AladinApiService aladinApiService) {
		this.planRepository = planRepository;
		this.aladinApiService = aladinApiService;
	}
	
	public Plan addPlan(int userId
						, String isbn13
						, LocalDate startDate
						, LocalDate endDate
						, int totalPage) {
		
		List<BookLookUpDto> lookUpBook = aladinApiService.getBookByIsbn(isbn13);
		
		String cover = lookUpBook.get(0).getCover();
		String title = lookUpBook.get(0).getTitle();
		String author = lookUpBook.get(0).getAuthor();
		
		Plan plan = Plan.builder()
						.userId(userId)
						.isbn13(isbn13)
						.cover(cover)
						.title(title)
						.author(author)
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
		
		List<Plan> planList = planRepository.findByUserIdAndFinished(userId, false);
		
		if(planList.size() > 0) {
			return planList.get(0);
		}else {
			return null;
		}	
	}
	
	public List<Plan> getPlanFinished(int userId) {
		
		List<Plan> planList = planRepository.findByUserIdAndFinished(userId, true);
		
		return planList;
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
