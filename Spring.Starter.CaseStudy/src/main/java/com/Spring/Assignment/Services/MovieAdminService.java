package com.Spring.Assignment.Services;

import org.springframework.stereotype.Service;

import com.Spring.Assignment.Models.Movie;
import com.Spring.Assignment.Repo.MovieRepository;

@Service
public class MovieAdminService {
		
	private MovieRepository repo;
	
	public MovieAdminService(MovieRepository reop) {		
				repo = reop;
			}
	
	
	
	public void addMovie(Movie movie) {
		try {
		repo.save(movie);
		
		}
		catch(Exception ex) {
			ex.fillInStackTrace();
		}
	}
	

	public void modifyMovie(String id, Movie movie) {
					
		Movie mov = repo.findById(id).orElse(null);
		mov = movie;
		repo.save(movie);
					
	}
		
}
