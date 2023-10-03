package com.lcwd.user.service.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lcwd.user.service.entities.User;

public interface UserService {
	
	// user operations 
	
	
	//create 
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
	
	
	
	

}
