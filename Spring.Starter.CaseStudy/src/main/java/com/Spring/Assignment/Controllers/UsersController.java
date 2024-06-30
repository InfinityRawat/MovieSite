package com.Spring.Assignment.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.Spring.Assignment.Models.Movie;
import com.Spring.Assignment.Services.MovieUsersService;


@Controller

public class UsersController {
	
	Logger logger  = LoggerFactory.getLogger(UsersController.class);

	 private MovieUsersService service;

	public UsersController(MovieUsersService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public String IndexPage( ) {
		logger.info("Log at info level of Index Page");
		return "Index";
	}
	
	@GetMapping("/searchBy/{id}")
	public String searchBy(@PathVariable String id, Model model) {
		logger.info("Log at info level of search Page {}",id);

			
		model.addAttribute("string",id);
		
		return "searchBy";
	}
	
	@GetMapping("/byId")
	public String movieGetId(@RequestParam("search") String id, Model model) {
		

		logger.info("Log at movieGetId Info level {}, {}",id);

		Movie movie =	service.byId(id);
		logger.info("Log at movieGetId Info level {}, {}",id, movie.getName());

		
		model.addAttribute("movie",movie);
		
		return "resultPage";
	}
	
	@GetMapping("/byName")
	public String movieGetName(@RequestParam("search") String name,Model model) {
		List<Movie> movie =	service.byName(name);
		model.addAttribute("movie",movie);
		return "resultPage";
	}
	
	@GetMapping("/byCollection")
	public String movieGetCollection(@RequestParam("from") int from, @RequestParam("to") int to, Model model) {
		
		List<Movie> movie =	service.byCollection(from,to);
		model.addAttribute("movie",movie);
		return "resultPage";
	}
}
