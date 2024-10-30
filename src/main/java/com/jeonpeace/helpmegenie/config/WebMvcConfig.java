package com.jeonpeace.helpmegenie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jeonpeace.helpmegenie.interceptor.PermissionInterceptor;
import com.jeonpeace.helpmegenie.common.FileManager;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry.addResourceHandler("/imagesTemp/**")
	  .addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/");
	  
	  registry.addResourceHandler("/imagesContents/**")
	  .addResourceLocations("file:///" + FileManager.TRUE_FILE_UPLOAD_PATH + "/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		PermissionInterceptor interceptor = new PermissionInterceptor();
		
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/static/**", "/images/**", "/user/logout");
	}
	
}