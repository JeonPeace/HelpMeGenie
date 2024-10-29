package com.jeonpeace.helpmegenie.report.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.report.domain.Like;
import com.jeonpeace.helpmegenie.report.repository.LikeRepository;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	public Like insertLike(int reportId, int userId) {
		
		Like like = Like.builder()
						.reportId(reportId)
						.userId(userId)
						.build();
		
		Like result = likeRepository.save(like);
		
		return result;
	}
	
	@Transactional
	public String deleteLike(int reportId, int userId) {

		Like like = likeRepository.findByReportIdAndUserId(reportId, userId);
		
		if(like != null) {
			likeRepository.deleteByReportIdAndUserId(reportId, userId);
			return "success";
		}else {
			return "fail";
		}
	}
	
	public int getLikeCount(int reportId) {
		
		int likeCount = likeRepository.countByReportId(reportId);
		
		return likeCount;
	}
	
	public boolean loginUserLike(int reportId, int userId) {
		
		Like like = likeRepository.findByReportIdAndUserId(reportId, userId);
		
		if(like != null) {
			return true;
		}else {
			return false;
		}		
	}
	
}
