package com.jeonpeace.helpmegenie.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeonpeace.helpmegenie.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByLoginId(String loginId);
	
}
