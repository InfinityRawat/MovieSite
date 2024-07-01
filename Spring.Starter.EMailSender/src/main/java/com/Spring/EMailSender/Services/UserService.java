package com.Spring.EMailSender.Services;

import com.Spring.EMailSender.Entity.UserDto;

public interface UserService {
	public UserDto saveUser(UserDto user);
	
	public Boolean verifyTokenAndEnableAccount(String token);
}
