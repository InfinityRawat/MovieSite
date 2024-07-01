package com.Spring.EMailSender.Controllers;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Spring.EMailSender.Entity.UserDto;
import com.Spring.EMailSender.Responses.HttpResponse;
import com.Spring.EMailSender.Services.UserService;

@RestController
@RequestMapping("/email")
public class UserController {
	
	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	
	
	@PostMapping
	public ResponseEntity<HttpResponse> saveUser(@RequestBody UserDto dto){
		
		service.saveUser(dto);
		return ResponseEntity.created(URI.create("")).body(
				HttpResponse.builder().status(HttpStatus.CREATED)
				.StatusCode(HttpStatus.CREATED.value())
				.message("User Created!")
				.data(Map.of("user",dto))
				.timeStamp(LocalDateTime.now().toString())
				.build()
				);
	}
	
	@GetMapping
	public ResponseEntity<HttpResponse> confirmUserAccount(@RequestParam String token){
		
		Boolean verifyToken = service.verifyTokenAndEnableAccount(token);
		return ResponseEntity.ok().body(
				HttpResponse.builder().status(HttpStatus.OK)
				.StatusCode(HttpStatus.OK.value())
				.message("User verified!")
				.data(Map.of("verified",verifyToken))
				.timeStamp(LocalDateTime.now().toString())
				.build()
				);
	}
	
}
