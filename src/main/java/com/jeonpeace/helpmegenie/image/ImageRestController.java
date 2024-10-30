package com.jeonpeace.helpmegenie.image;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jeonpeace.helpmegenie.common.FileManager;
import com.jeonpeace.helpmegenie.image.service.ImageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/image")
public class ImageRestController {
	
	private ImageService imageService;
	
	public ImageRestController(ImageService imageService) {
		this.imageService = imageService;
	}
	

	@PostMapping("/save")
	public String saveImage(@RequestParam("imageFile") MultipartFile file
							, @RequestParam("planId") int planId
							, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		String url = imageService.saveTempImage(userId, planId, file);
		
		return url;
	}
	
	@PostMapping("/deleteAtCreate")
	public void deleteImageCreate(@RequestParam("imagePath") String imagePath) {
		
		imageService.deleteImage(imagePath);
		
		FileManager.removeFileForSave(imagePath);	
	}
	
	@PostMapping("/deleteAtModify")
	public void deleteImageModify(@RequestParam("imagePath") String imagePath) {
		
		imageService.deleteImage(imagePath);		
		
		FileManager.removeFile(imagePath);		
	}
	
}

