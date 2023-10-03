package com.lcwd.rating.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcwd.rating.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating,String>{

	//custom defined methods
	
	List<Rating> findByUserId(String userId);
	
	//
	 List<Rating> findByHotelId(String hotelId);
	
	
	
}
