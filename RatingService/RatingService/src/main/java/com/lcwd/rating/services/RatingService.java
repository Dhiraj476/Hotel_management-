package com.lcwd.rating.services;

import java.util.List;

import com.lcwd.rating.entities.Rating;

public interface RatingService {
	
	
	//create
	
	
	
	Rating create(Rating rating);
	
	//getall  by hotel

	List<Rating> getAll();
	
	// get all by userId
	
	List<Rating> getRatingByUserId(String userId);
	
	// get rating by 
	List<Rating> getRatingByHoteId(String hotelId); 
}
