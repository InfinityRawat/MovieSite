package com.Spring.EMailSender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.EMailSender.Entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long>{
		
	UserEntity findByEmailIgnoreCase(String email);
	Boolean existsByEmail(String email);
}	
