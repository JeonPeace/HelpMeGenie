package com.jeonpeace.helpmegenie.image.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	public String saveTempImage(int userId, int planId, MultipartFile file) {
		
		String urlPath;
		try {
			urlPath = FileManager.tempSaveFile(userId, file);
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
	
	public void saveRealImage(int reportId, int planId, String imagePath) {
		
		Image image = Image.builder()
						   .planId(planId)
						   .reportId(reportId)
						   .imagePath(imagePath)
						   .build();
		
		imageRepository.save(image);
		
	}
	
	public void imageListSet(List<String> urlList, int reportId, int planId) {
		
		for(String imagePath:urlList) {
			
			deleteImage(imagePath);
			
			saveRealImage(reportId, planId, imagePath);
			
		}
		
	}
	
	@Transactional
	public void deleteImage(String imagePath) {
		
		imageRepository.deleteByImagePath(imagePath);
		
	}
	
}
