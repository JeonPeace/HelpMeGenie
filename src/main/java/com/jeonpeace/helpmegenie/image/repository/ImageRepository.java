package com.jeonpeace.helpmegenie.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeonpeace.helpmegenie.image.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}
