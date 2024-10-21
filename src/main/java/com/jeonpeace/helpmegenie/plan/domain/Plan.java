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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="plan")
@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private int id;
	
	private int userId;
	
	private String isbn13;
	
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
