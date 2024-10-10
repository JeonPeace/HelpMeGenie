package com.jeonpeace.helpmegenie.user.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jeonpeace.helpmegenie.common.HashingEncoder;
import com.jeonpeace.helpmegenie.user.domain.User;
import com.jeonpeace.helpmegenie.user.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private HashingEncoder encoder;
	
	public UserService(UserRepository userRepository, @Qualifier("md5Hashing") HashingEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	public User addUser(String loginId, String password, String name, String email) {
		
		String encryptPassword = encoder.encode(password);
		
		User user = User.builder()
					.loginId(loginId)
					.password(encryptPassword)
					.name(name)
					.email(email)
					.build();

		User result = userRepository.save(user);

		return result;	
	}
	
	public boolean isDuplicateId(String loginId) {
		
		boolean isDuplicate = false;
		
		User user = userRepository.findByLoginId(loginId);
		
		if(user != null) {
			isDuplicate = true;
		}else {
			isDuplicate = false;
		}
		
		return isDuplicate;
	}
	
	public User getUser(String loginId, String password) {
		
		String encryptPassword = encoder.encode(password);
		
		User user = userRepository.findByPasswordAndLoginId(encryptPassword, loginId);
		
		return user;
	}
}
