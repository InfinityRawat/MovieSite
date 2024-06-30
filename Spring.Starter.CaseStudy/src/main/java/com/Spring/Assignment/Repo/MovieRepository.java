package com.Spring.Assignment.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.Assignment.Models.Movie;

public interface MovieRepository extends JpaRepository<Movie,String> {
	
//	@Query("select * from Movie where username = '%${name}%'")findByTitleContainingIgnoreCase
	List<Movie> findAllByNameContainingIgnoreCase(String name);
		
	Optional<Movie> findById(String id);
	List<Movie> findAllByCollectionBetween(int from,int to);

}
