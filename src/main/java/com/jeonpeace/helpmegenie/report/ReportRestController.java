package com.jeonpeace.helpmegenie.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeonpeace.helpmegenie.image.service.ImageService;
import com.jeonpeace.helpmegenie.report.domain.Comment;
import com.jeonpeace.helpmegenie.report.domain.Like;
import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.service.CommentService;
import com.jeonpeace.helpmegenie.report.service.LikeService;
import com.jeonpeace.helpmegenie.report.service.ReportService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/report")
public class ReportRestController {

	private ReportService reportService;
	private CommentService commentService;
	private LikeService likeService;
	private ImageService imageService;
	
	public ReportRestController(ReportService reportService, CommentService commentService, LikeService likeService, ImageService imageService) {
		this.reportService = reportService;
		this.commentService = commentService;
		this.likeService = likeService;
		this.imageService = imageService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createReport(@RequestParam("contents") String contents
										    , @RequestParam("planId") int planId
										    , @RequestParam("urlList") List<String> urlList
											, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Report tempReport = reportService.addTempReport(userId, planId, contents);
		
		Report report = reportService.addRealReport(tempReport);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(report != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		imageService.imageListSet(urlList, report.getId(), report.getPlanId());
		
		return resultMap;
	}

	@PostMapping("/comment/create")
	public Map<String, String> createComment(@RequestParam("reportId") int reportId
											, @RequestParam("commentText") String commentText
											, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Comment comment = commentService.addComment(reportId, userId, commentText);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(comment != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/comment/delete")
	public Map<String, String> deleteComment(@RequestParam("commentId") int commentId){
		
		String result = commentService.deleteComment(commentId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(result == "success") {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;	
	}	
	
	@PostMapping("/like")
	public Map<String, String> addLike(@RequestParam("reportId") int reportId
									, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Like like = likeService.insertLike(reportId, userId);

		Map<String, String> resultMap = new HashMap<>();
		
		if(like != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;		
	}
	
	@DeleteMapping("/unlike")
	public Map<String, String> unlike(@RequestParam("reportId") int reportId
									, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		String result = likeService.deleteLike(reportId, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(result == "success") {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;	
	}		
}
