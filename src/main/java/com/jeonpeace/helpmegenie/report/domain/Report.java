package com.jeonpeace.helpmegenie.report.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="`report`")
@Entity
public class Report {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="planId")
	private int planId;
	
	@Lob
	private String contents;
	
	@Column(name="createdAt")
	@CreationTimestamp	
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp	
	private LocalDateTime updatedAt;
	
}
