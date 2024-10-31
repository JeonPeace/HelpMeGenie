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
	
	public String saveModifyImage(int userId, int planId, int reportId, MultipartFile file) {
		
		String urlPath;
		try {
			urlPath = FileManager.saveFile(userId, file);
		} catch (Exception e) {
			return null;
		}
		
		Image image = Image.builder()
						   .planId(planId)
						   .reportId(reportId)
						   .imagePath(urlPath)
						   .build();
		
		imageRepository.save(image);
						   
		return urlPath;
	}
	
	public void imageListSet(List<String> urlList, int reportId) {
		
		for(String imagePath:urlList) {
			
			Image image = imageRepository.findByImagePath(imagePath);
			
			Image updateImage = image.toBuilder()
									 .reportId(reportId)
									 .build();
			
			imageRepository.save(updateImage);
		}
		
	}
	
	@Transactional
	public void deleteImage(String imagePath) {
			
		imageRepository.deleteByImagePath(imagePath);
		FileManager.removeFile(imagePath);
		
	}
	
	@Transactional
	public void deleteImageByReportId(int reportId) {
			
		List<Image> imageList = imageRepository.findByReportId(reportId);
		
		for(Image image:imageList) {
			FileManager.removeFile(image.getImagePath());
		}
		
		imageRepository.deleteByReportId(reportId);
		
	}
	
}
