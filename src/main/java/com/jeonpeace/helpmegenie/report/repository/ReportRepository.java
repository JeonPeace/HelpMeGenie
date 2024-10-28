package com.jeonpeace.helpmegenie.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeonpeace.helpmegenie.report.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{

	public List<Report> findTop5ByOrderByIdDesc();
	
	public List<Report> findByOrderByIdDesc();
	
}
