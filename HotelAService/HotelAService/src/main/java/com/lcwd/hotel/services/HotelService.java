package com.lcwd.hotel.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.repositories.HotelRepositories;

public interface HotelService {
	//create 
	
	
	Hotel create(Hotel hotel);
	
	
	
	//getall
	
	List<Hotel> getAll();
	
	//get single
	Hotel get(String id);

}
