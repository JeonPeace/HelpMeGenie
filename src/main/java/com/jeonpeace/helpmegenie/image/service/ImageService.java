package com.jeonpeace.helpmegenie.image.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jeonpeace.helpmegenie.common.FileManager;
import com.jeonpeace.helpmegenie.image.domain.Image;
import com.jeonpeace.helpmegenie.image.repository.ImageRepository;

@Service
public class ImageService {

	private ImageRepository imageRepository;
	
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	public String saveImage(int userId, int planId, MultipartFile file) {
		
		String urlPath;
		try {
			urlPath = FileManager.saveFile(userId, file);
		} catch (Exception e) {
			return null;
		}
		
		Image image = Image.builder()
						   .planId(planId)
						   .imagePath(urlPath)
						   .build();
		
		imageRepository.save(image);
						   
		return urlPath;
	}
	
	
}
