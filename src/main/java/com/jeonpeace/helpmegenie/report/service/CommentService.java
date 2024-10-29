package com.jeonpeace.helpmegenie.report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.report.domain.Comment;
import com.jeonpeace.helpmegenie.report.repository.CommentRepository;
import com.jeonpeace.helpmegenie.user.domain.User;
import com.jeonpeace.helpmegenie.user.service.UserService;

@Service
public class CommentService {

	private UserService userService;
	private CommentRepository commentRepository;
	
	public CommentService(UserService userService, CommentRepository commentRepository) {
		this.userService = userService;
		this.commentRepository = commentRepository;
	}
	
	public Comment addComment(int reportId, int userId, String commentText) {
		
		User user = userService.getUserById(userId);
		
		String userLoginId = user.getLoginId();
		
		Comment comment = Comment.builder()
								 .reportId(reportId)
								 .userId(userId)
								 .userLoginId(userLoginId)
								 .commentText(commentText)
								 .build();
		
		Comment result = commentRepository.save(comment);
		
		return result;
	}
	
	public List<Comment> getComment(int reportId){
		
		List<Comment> commentList = new ArrayList<>();
		
		commentList = commentRepository.findByReportId(reportId);
		
		return commentList;
	}
	
	@Transactional
	public String deleteComment(int commentId) {
		
		Optional<Comment> optionalComment = commentRepository.findById(commentId);
		
		Comment comment = optionalComment.orElse(null);
		
		if(comment != null) {
			commentRepository.deleteById(commentId);
			return "success";
		}else {
			return "fail";
		}
	}
	
	public int getCommentCount(int reportId) {
		
		int commentCount = commentRepository.countByReportId(reportId);
		
		return commentCount;
	}
	
}
