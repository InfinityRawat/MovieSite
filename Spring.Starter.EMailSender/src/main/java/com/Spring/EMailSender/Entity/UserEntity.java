package com.Spring.EMailSender.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name ="users")
public class UserEntity {
	
	@SequenceGenerator(name = "UserIdSeq", sequenceName = "UserIdSeq", initialValue = 1, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UserIdSeq" )
	private long id;
	private String name;
	private String password;
	private String email;
	private boolean isEnabled;
}
