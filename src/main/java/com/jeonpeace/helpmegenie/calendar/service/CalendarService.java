package com.jeonpeace.helpmegenie.calendar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.plan.dto.PlanList;
import com.jeonpeace.helpmegenie.plan.service.PlanService;

@Service
public class CalendarService {

	private PlanService planService;
	
	public CalendarService(PlanService planService) {
		this.planService = planService;
	}
	
	public List<Map<String, Object>> getCalendarList(int userId){
		
		 List<PlanList> planList = planService.getPlanUnFinished(userId);
		 
		 List<Map<String, Object>> dataList = new ArrayList<>();

		 for(PlanList plan:planList) {
			 
			 Map<String, Object> data = new HashMap<>();
			 
			 data.put("title", plan.getTitle());
			 data.put("start", plan.getStartDate());
			 data.put("end", plan.getEndDate());
			 
			 dataList.add(data);
		 }
		 
		 return dataList;
	}
	
}
