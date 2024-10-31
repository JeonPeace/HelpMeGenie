package com.jeonpeace.helpmegenie.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.report.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	public List<Comment> findByReportId(int reportId);
	
	public int countByReportId(int reportId);
	
	@Transactional
	public void deleteByReportId(int reportId);
	
}
