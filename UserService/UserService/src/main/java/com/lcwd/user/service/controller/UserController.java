package com.lcwd.user.service.controller;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import com.lcwd.user.service.services.Impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	// create 
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
		
	}
	
	// single user get
	@GetMapping("/{userId}")
//	@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelFallback")
//@Retry(name="ratingHotelService",fallbackMethod="ratingHotelFallback")
	@RateLimiter(name="userRateLimiter",fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
	User user = userService.getUser(userId);	
	return ResponseEntity.ok(user);
	}
	
	int retryCount=1;
	//creating fall back method for circuitbreakern
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex)
	{

		// logger.info("Fallback is executed because service is down",ex.getMessage());
		logger.info("get single user handler");
		//logger.info("Retry Count:{}", retryCount);
	//	retryCount++;
		User user = User.builder().email("testing@gmail.com").name("dummy").about("this user is created because some service is down").userId("1245456").build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	//all
	@GetMapping
	public ResponseEntity<List<User>>  getAllUser()
	{
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
		}
	

}
