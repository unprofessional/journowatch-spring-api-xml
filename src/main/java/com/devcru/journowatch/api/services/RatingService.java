package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.devcru.journowatch.api.daoimpl.RatingDaoImpl;
import com.devcru.journowatch.api.objects.Rating;

public class RatingService {
	
	@Autowired
	private static RatingDaoImpl rd;
	
	/*
	 * CRUD stuff
	 */
	
	public boolean createRating(Rating rating) {
		boolean status = rd.createRating(rating);
		return status;
	}
	
	public Rating getRating(Rating rating) {
		return rd.getRating(rating);
	}
	
	public boolean updateRating(Rating rating) {
		boolean status = false;
		return status;
	}
	
	public boolean deleteRating(Rating rating) {
		boolean status = false;
		return status;
	}
	
	/*
	 * Supporting business-logic stuff
	 */

}
