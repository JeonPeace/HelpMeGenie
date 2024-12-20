package com.jeonpeace.helpmegenie.plan.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="`plan`")
@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	private int userId;
	
	@Column(name="isbn13")
	private String isbn13;
	
	@Column(name="cover")
	private String cover;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="startDate")
	private LocalDate startDate;
	
	@Column(name="endDate")
	private LocalDate endDate;
	
	@Column(name="totalPage")
	private int totalPage;
	
	@Column(name="donePage")
	private int donePage;
	
	private boolean finished;

	@Column(name="createdAt")
	@CreationTimestamp	
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp	
	private LocalDateTime updatedAt;
	
}
