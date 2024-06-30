package com.Spring.Assignment.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Spring.Assignment.Models.Movie;
import com.Spring.Assignment.Services.MovieAdminService;

import jakarta.validation.Valid;

@Controller
public class AdminController {
	
	private MovieAdminService service;
	
	public AdminController(MovieAdminService service) {
		this.service = service;
	}

	@GetMapping("/admin")
	public String admin() {
		
		return "admin";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/AddMovie")
	public String addMovie(Movie movie) {	
		return "AddMovie";
	}
	
	@PostMapping("/add")
	public String addIt(@Valid Movie movie, BindingResult  result) {
				if(result.hasErrors()) {
			return "AddMovie";
		}
		
		service.addMovie(movie);
//		 we can also leave Post-Redirect-Get as hibernate first use select query to check if the object already exist in db or not 
			//ifexist it will not going to save it again
		return "redirect:/postSucess";
			
	}
	
	@GetMapping("/postSucess")
	public String postSucess() {
//		 don't need to Post-Redirect-Get as hibernate first use select query to check if the object already exist in db or not 

		return "AddedSucess";
			
	}
	
	@GetMapping("/Modify")
	public String modify() {
		return "ModifyMovie";
			
	}

}






















