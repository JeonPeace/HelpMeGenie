package com.jeonpeace.helpmegenie.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeonpeace.helpmegenie.user.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByLoginId(String loginId);
	
	public User findByPasswordAndLoginId(String password, String loginId);
	
	@Query(value="SELECT `loginId` FROM `user` WHERE `id` = :userId", nativeQuery=true)
	public String getLoginIdById(@Param("userId") int userId);
	
}
