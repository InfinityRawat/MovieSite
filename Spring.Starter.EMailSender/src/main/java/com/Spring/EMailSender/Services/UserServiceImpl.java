package com.Spring.EMailSender.Services;

import org.springframework.stereotype.Service;

import com.Spring.EMailSender.Entity.Confirmation;
import com.Spring.EMailSender.Entity.UserDto;
import com.Spring.EMailSender.Entity.UserEntity;
import com.Spring.EMailSender.MailSender.MailSender;
import com.Spring.EMailSender.Repository.ConfirmationRepo;
import com.Spring.EMailSender.Repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	private UserRepo userRepo;
	private ConfirmationRepo confRepo;
	private MailSender mailSender;


	

	public UserServiceImpl(UserRepo userRepo, ConfirmationRepo confRepo, MailSender mail) {
		super();
		this.userRepo = userRepo;
		this.confRepo = confRepo;
		this.mailSender = mail;
	}

	@Override
	public UserDto saveUser(UserDto user)   {
		
		if(userRepo.findByEmailIgnoreCase(user.getEmail())!=null) {
			throw new RuntimeException("User Already exists");
		}
		
			UserEntity entity = new UserEntity();
			entity.setEnabled(false);
			entity.setName(user.getName());
			entity.setPassword(user.getPassword());
			entity.setEmail(user.getEmail());
			userRepo.save(entity);
			Confirmation conf = new Confirmation(entity);
			confRepo.save(conf);
			
//			Todo send email to user with token
			
//			mailSender.sendSimpleMailMessage(entity.getName(), entity.getEmail(), conf.getToken());
//			mailSender.sendMimeMessageWithAttachments(entity.getName(), entity.getEmail(), conf.getToken());
//			mailSender.sendHtmlEmail(entity.getName(), entity.getEmail(), conf.getToken());
			mailSender.sendHtmlEmailWithEmbeddedFile(entity.getName(), entity.getEmail(), conf.getToken());

			return user;
	}

	@Override
	public Boolean verifyTokenAndEnableAccount(String token) {
		Confirmation conf = confRepo.findByToken(token);
		UserEntity user = userRepo.findByEmailIgnoreCase(conf.getUser().getEmail());
		if(user==null) {
			return false;
		}
		
		user.setEnabled(true);
		userRepo.save(user);
		
		return true;
	}

}
