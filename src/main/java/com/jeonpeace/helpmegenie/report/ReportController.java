package com.jeonpeace.helpmegenie.report;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.service.PlanService;
import com.jeonpeace.helpmegenie.report.domain.Comment;
import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.dto.Gallery;
import com.jeonpeace.helpmegenie.report.service.CommentService;
import com.jeonpeace.helpmegenie.report.service.GalleryService;
import com.jeonpeace.helpmegenie.report.service.LikeService;
import com.jeonpeace.helpmegenie.report.service.ReportService;
import com.jeonpeace.helpmegenie.user.domain.User;
import com.jeonpeace.helpmegenie.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/report")
public class ReportController {

	private PlanService planService;
	private GalleryService galleryService;
	private ReportService reportService;
	private UserService userService;
	private CommentService commentService;
	private LikeService likeService;


	public ReportController(PlanService planService, GalleryService galleryService, ReportService reportService, UserService userService, CommentService commentService, LikeService likeService) {
		this.planService = planService;
		this.galleryService = galleryService;
		this.reportService = reportService;
		this.userService = userService;
		this.commentService = commentService;
		this.likeService = likeService;
	}
	
	
	@GetMapping("/choose-view")
	public String chooseView(HttpSession session, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Plan> planList = planService.getPlanFinished(userId);		
		model.addAttribute("planList", planList);
		
		return "/report/choose";
	}
	
	@GetMapping("/create-view")
	public String createView(@RequestParam("planId") int planId
							, Model model) {
		
		Plan plan = planService.findPlanById(planId);
		model.addAttribute("plan", plan);
		
		return "/report/create";
	}
	
	@GetMapping("/gallery-view")
	public String galleryView(HttpSession session
							 , Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Gallery> galleryList = galleryService.getReportList(userId);
		model.addAttribute("galleryList", galleryList);
		
		return "/report/gallery";
	}
	
	@GetMapping("/detail-view")
	public String reportDetailView(@RequestParam("reportId") int reportId
								 , HttpSession session
								 , Model model) {
		
		Report report = reportService.getReport(reportId);
		model.addAttribute("report", report);
		
		int commentCount = commentService.getCommentCount(reportId);
		model.addAttribute("commentCount", commentCount);
		
		int likeCount = likeService.getLikeCount(reportId);
		model.addAttribute("likeCount", likeCount);
		
		Plan plan = planService.findPlanById(report.getPlanId());
		model.addAttribute("plan", plan);
		
		User user = userService.getUserById(plan.getUserId());
		model.addAttribute("user", user);
		
		int userId = (Integer)session.getAttribute("userId");
		boolean loginUserLike = likeService.loginUserLike(reportId, userId);
		model.addAttribute("loginUserLike", loginUserLike);
		
		return "/report/detail";
	}
	
	@GetMapping("/comment-view")
	public String reportCommentView(@RequestParam("reportId") int reportId
								  , HttpSession session
								  , Model model) {
		
		Report report = reportService.getReport(reportId);
		model.addAttribute("report", report);
		
		int commentCount = commentService.getCommentCount(reportId);
		model.addAttribute("commentCount", commentCount);
		
		int likeCount = likeService.getLikeCount(reportId);
		model.addAttribute("likeCount", likeCount);		
		
		Plan plan = planService.findPlanById(report.getPlanId());
		model.addAttribute("plan", plan);
		
		User user = userService.getUserById(plan.getUserId());		
		model.addAttribute("user", user);
		
		List<Comment> commentList = commentService.getComment(reportId);		
		model.addAttribute("commentList", commentList);
		
		int userId = (Integer)session.getAttribute("userId");
		boolean loginUserLike = likeService.loginUserLike(reportId, userId);
		model.addAttribute("loginUserLike", loginUserLike);		
		
		return "/report/comment";
	}
	
	@GetMapping("/modify-view")
	public String modifyReportView(@RequestParam("reportId") int reportId
								  , Model model) {
		
		Report report = reportService.getReport(reportId);
		model.addAttribute("report", report);	
		
		Plan plan = planService.findPlanById(report.getPlanId());
		model.addAttribute("plan", plan);
		
		return "/report/modify";
	}
	
}
