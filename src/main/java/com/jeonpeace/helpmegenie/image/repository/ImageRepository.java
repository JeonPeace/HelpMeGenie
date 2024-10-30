package com.jeonpeace.helpmegenie.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.jeonpeace.helpmegenie.image.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

	@Transactional
	public void deleteByImagePath(String imagePath);
	
}
