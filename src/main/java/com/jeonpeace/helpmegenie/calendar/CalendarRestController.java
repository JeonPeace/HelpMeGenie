package com.jeonpeace.helpmegenie.calendar;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeonpeace.helpmegenie.calendar.service.CalendarService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/calendar")
public class CalendarRestController {

	private CalendarService calendarService;
	
	public CalendarRestController(CalendarService calendarService) {
		this.calendarService = calendarService;
	}
	
	@GetMapping("/data")
	public List<Map<String, Object>> getCalendarData(HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Map<String, Object>> dataList = calendarService.getCalendarList(userId);
		
		return dataList;
	}
	
}
