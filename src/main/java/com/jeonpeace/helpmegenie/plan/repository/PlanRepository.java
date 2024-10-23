package com.jeonpeace.helpmegenie.plan.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.plan.domain.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer>{
	
	public List<Plan> findByUserIdAndFinished(int userId, boolean Finished);
	
	@Query(value="SELECT `isbn13` FROM `plan` WHERE `id` = :planId", nativeQuery=true)
	public String getIsbn13ById(@Param("planId") int planId);
	
	public Plan findById(int planId);
	
	@Transactional
	public void deleteById(int planId);
	
}
