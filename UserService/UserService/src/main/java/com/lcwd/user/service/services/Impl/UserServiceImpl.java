package com.lcwd.user.service.services.Impl;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelservice;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		String randomUserId= UUID.randomUUID().toString()	;
		user.setUserId(randomUserId);
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
		
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
   
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id doesn't exist on server"));
		//fetch rating of above user from Rating Service
		//http://localhost:8085/ratings/users/4d6f32a8-a994-4571-b2d0-6e508597b584
		
Rating[] lis = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
            List<Rating> list = Arrays.stream(lis).toList();
            		List<Rating> ratingList = list.stream().map(rating->{
//	ResponseEntity<Hotel> hotell = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
	Hotel hotel = hotelservice.getHotel(rating.getHotelId());
	rating.setHotel(hotel);
     
      return  rating;
}).collect(Collectors.toList());
 
	
	
	
logger.info("{}",ratingList);
 user.setRatings(ratingList);
		return user;
	}
	
	

}
