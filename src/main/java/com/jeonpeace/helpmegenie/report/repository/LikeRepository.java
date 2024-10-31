package com.jeonpeace.helpmegenie.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.report.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{

	public int countByReportId(int reportId);
	
	@Transactional
	public void deleteByReportId(int reportId);
	
	public Like findByReportIdAndUserId(int reportId, int userId);
	
	@Transactional
	public void deleteByReportIdAndUserId(int reportId, int userId);
	
}
