package com.lcwd.rating.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repositories.RatingRepository;
import com.lcwd.rating.services.RatingService;
@Service
public class RatingServiceImpl implements RatingService{

	@Autowired
	private RatingRepository repository;
	
	
	@Override
	public Rating create(Rating rating) {
		// TODO Auto-generated method stub
		String ratingUUID =UUID.randomUUID()
.toString()	;
	    rating.setRatingId(ratingUUID);	
		return repository.save(rating);
	}

	@Override
	public List<Rating> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		// TODO Auto-generated method stub
		return repository.findByUserId(userId);
	}

	@Override
	public List<Rating> getRatingByHoteId(String hotelId) {
		// TODO Auto-generated method stub
		return repository.findByHotelId(hotelId);
	}

}
