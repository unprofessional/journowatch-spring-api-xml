package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcru.journowatch.api.daoimpl.RatingDaoImpl;
import com.devcru.journowatch.api.objects.Rating;

/**
 * @author Monitored We are going to make use of a "Service layer" this time
 *         around to separate business logic from the presentation layer
 *         (Controllers)
 *
 */

@Service
public class RatingService {
	
	@Autowired
	private RatingDaoImpl rd;
	
	/*
	 * CRUD stuff
	 */
	
	public boolean createRating(Rating rating) {
		
		System.out.println("RS > rating.getUuid: " + rating.getUuid());
		System.out.println("RS > rating.getTimestamp: " + rating.getTimestamp());
		System.out.println("RS > rating.getOwner: " + rating.getOwneruuid());
		System.out.println("RS > rating.getJourno: " + rating.getJournouuid());
		System.out.println("RS > rating.getScore: " + rating.getScore());
		System.out.println("RS > rating.getComment: " + rating.getComment());
		
		boolean status = rd.createRating(rating);
		return status;
	}
	
	public Rating getRating(Rating rating) {
		return rd.getRating(rating);
	}
	
	public boolean updateRating(Rating rating) {
		boolean status = rd.updateRating(rating);
		return status;
	}
	
	public boolean deleteRating(Rating rating) {
		boolean status = rd.deleteRating(rating);
		return status;
	}
	
	/*
	 * Supporting business-logic stuff
	 */

	// Nothing yet.

}
