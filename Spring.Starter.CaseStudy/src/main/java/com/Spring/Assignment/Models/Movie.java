package com.Spring.Assignment.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="MOVIES")
public class Movie {
		

	@Id
	@NotNull(message=" Please provide a valid Id")
	@NotEmpty(message=" Movie Id cannot be null")
	private String id;
	
	@NotNull
	@NotEmpty(message = "Movie name cannot be empty")
	private String name;
	
	@NotNull(message="Collection cannot be empty")
	@Min(value=100)
	private int collection;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Movie [name=" + name + ", id=" + id + ", collection=" + collection + "]";
	}
	public Movie(String id, String name, int collection) {
		super();
		this.name = name;
		this.id = id;
		this.collection = collection;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCollection() {
		return collection;
	}
	public void setCollection(int collection) {
		this.collection = collection;
	}

	
}
