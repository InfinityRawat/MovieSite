package com.Spring.Assignment.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Spring.Assignment.Models.Movie;
import com.Spring.Assignment.Repo.MovieRepository;

import jakarta.annotation.PostConstruct;

@Service
public class MovieUsersService {
		
	private MovieRepository repo;
	
	public MovieUsersService(MovieRepository reop) {		
				repo = reop;
			}
	
	@PostConstruct
	public void poppulate() {
		
		List<Movie> movies = new ArrayList<>();
		
		movies.addAll(Arrays.asList(new Movie("M001" ,"The Mummy", 3000000),
				new Movie("M010" ,"Star Wars", 7000000),
				new Movie("M006" ,"Mummy Returns", 7000000),
				new Movie("M002" ,"The Firm", 1000000),
				new Movie("M003" ,"Mr. Bean", 2500000),
				new Movie("M004" ,"Logan", 2500000),
				new Movie("M005" ,"The Avengers: Age of Ultron", 6000000)
				));
				
		repo.saveAll(movies);
	}
		
	public Movie byId(String id) {
		
		Optional<Movie> movie =repo.findById(id);
		return movie.orElse(null);
	}
	

	public List<Movie> byName(String name) {
					
		List<Movie> mov = repo.findAllByNameContainingIgnoreCase(name);
		
		System.out.println(mov);
		return mov;
					
	}
	
	public List<Movie> byCollection(int from, int to) {
		
		List<Movie> mov = repo.findAllByCollectionBetween(from,to);
		System.out.println(mov);
		return mov;
					
	}
		
}
