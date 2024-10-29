package com.jeonpeace.helpmegenie.report.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.plan.domain.Plan;
import com.jeonpeace.helpmegenie.plan.repository.PlanRepository;
import com.jeonpeace.helpmegenie.report.domain.Like;
import com.jeonpeace.helpmegenie.report.domain.Report;
import com.jeonpeace.helpmegenie.report.dto.Gallery;
import com.jeonpeace.helpmegenie.report.repository.CommentRepository;
import com.jeonpeace.helpmegenie.report.repository.LikeRepository;
import com.jeonpeace.helpmegenie.report.repository.ReportRepository;
import com.jeonpeace.helpmegenie.user.repository.UserRepository;

@Service
public class GalleryService {

	private ReportRepository reportRepository;
	private PlanRepository planRepository;
	private UserRepository userRepository;
	private CommentRepository commentRepository;
	private LikeRepository likeRepository;
	
	public GalleryService(ReportRepository reportRepository, PlanRepository planRepository, UserRepository userRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
		this.reportRepository = reportRepository;
		this.planRepository = planRepository;
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
		this.likeRepository = likeRepository;
	}
	
	public List<Gallery> getReportList(int nowLoginUserId){
		
		List<Report> reportList = reportRepository.findTop5ByOrderByIdDesc();
		
		List<Gallery> galleryList = new ArrayList<>();
		
		for(Report report:reportList) {
			
			int planId = report.getPlanId();
			
			int commentCount = commentRepository.countByReportId(report.getId());
			int likeCount = likeRepository.countByReportId(report.getId());
			String userLoginId = userRepository.getLoginIdById(report.getUserId());
			
			Like like = likeRepository.findByReportIdAndUserId(report.getId(), nowLoginUserId);
			Boolean loginUserLike = false;

			if(like != null) {
				loginUserLike = true;
			}
			
			Plan plan = planRepository.findById(planId);
			
			Gallery gallery = Gallery.builder()
									 .reportId(report.getId())
									 .userId(report.getUserId())
									 .userLoginId(userLoginId)
									 .contents(report.getContents())
									 .cover(plan.getCover())
									 .title(plan.getTitle())
									 .author(plan.getAuthor())
									 .commentCount(commentCount)
									 .likeCount(likeCount)
									 .loginUserLike(loginUserLike)
									 .build();
			
			
			galleryList.add(gallery);
		}
		
		return galleryList;
	}
	
}
