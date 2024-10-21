package com.jeonpeace.helpmegenie.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeonpeace.helpmegenie.plan.domain.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer>{

	public Plan findByUserId(int userId);
	
}
