package com.Spring.EMailSender.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="confirmations")
public class Confirmation {
	
	@SequenceGenerator(name = "UserIdSeq", sequenceName = "UserIdSeq", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UserIdSeq" )
	private long id;
	
	private String token;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime creationDate;
	
	
	@OneToOne(targetEntity = UserEntity.class,fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "users_id")
	private UserEntity user;
	
	public Confirmation(UserEntity user) {
		this.user = user;
		this.creationDate=LocalDateTime.now();
		this.token = UUID.randomUUID().toString();
		
	}
	
	
}
