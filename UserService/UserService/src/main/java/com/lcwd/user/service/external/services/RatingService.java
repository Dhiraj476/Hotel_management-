package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name="RATING-SERVICE")
public interface RatingService 
{
// get 
	
	//post
	@PostMapping("/ratings")
	public Rating createRating(Rating values);

	
	
	//update 
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable String ratingId,Rating values);
	
	//public void deleteRating(@PathVariable String ratingId);
	
	
}
