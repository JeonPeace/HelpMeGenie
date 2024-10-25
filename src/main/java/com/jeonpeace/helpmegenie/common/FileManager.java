package com.jeonpeace.helpmegenie.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {

	// 상수
	public static final String FILE_UPLOAD_PATH = "C:\\jeonpeace\\upload\\helpmegenie";
	
	// 파일 저장
	public static String saveFile(int userId, MultipartFile file) throws Exception {
		
		if(file.isEmpty()) {
			throw new Exception("Failed to store empty file " + file.getOriginalFilename());
		}
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 폴더 생성
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(directoryPath);
		
		if(!directory.mkdir()) {
			// 폴더 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		byte[] bytes;
		try {
			bytes = file.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
		} catch (IOException e) {
			// 파일 저장 실패
			return null;
		}

		
		// 저장된 파일을 접근할 URL Path 만들기
		// 파일 저장 경로 : "C:\\jeonpeace\\upload\\helpmegenie/2_8120980/test.png";
		// URL path : /images/2_8120980/test.png
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	public static boolean removeFile(String filePath) { // /images/2_8120980/test.png
		
		if(filePath == null) {
			return false;
		}
		
		// 파일 저장 경로 : "C:\\jeonpeace\\upload\\helpmegenie/2_8120980/test.png";
		String fullFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(fullFilePath);
		
		// 폴더 경로 : "C:\\jeonpeace\\upload\\helpmegenie";
		// 폴더(디렉토리)제거
		Path directoryPath = path.getParent();
		
		// 파일과 폴터(디렉토리) 존재하는지 확인
		if(Files.exists(path) && Files.exists(directoryPath)) {
			try {
				Files.delete(path);
				Files.delete(directoryPath);
			} catch (IOException e) {
				return false;
			}
		}
		
		return true;
	}
	
}
