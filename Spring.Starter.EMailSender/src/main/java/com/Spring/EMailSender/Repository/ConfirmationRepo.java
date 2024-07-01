package com.Spring.EMailSender.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.EMailSender.Entity.Confirmation;

public interface ConfirmationRepo extends JpaRepository<Confirmation, Long> {
		Confirmation findByToken(String token);
		
}
