package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcru.journowatch.api.daoimpl.JournoRatingDaoImpl;
import com.devcru.journowatch.api.objects.JournoRating;

/**
 * @author Monitored We are going to make use of a "Service layer" this time
 *         around to separate business logic from the presentation layer
 *         (Controllers)
 *
 */

@Service
public class JournoRatingService {
	
	@Autowired
	private JournoRatingDaoImpl rd;
	
	/*
	 * CRUD stuff
	 */
	
	public boolean createRating(JournoRating journorating) {
		
		System.out.println("RS > journorating.getUuid: " + journorating.getUuid());
		System.out.println("RS > journorating.getTimestamp: " + journorating.getTimestamp());
		System.out.println("RS > journorating.getOwner: " + journorating.getOwneruuid());
		System.out.println("RS > journorating.getJourno: " + journorating.getJournouuid());
		System.out.println("RS > journorating.getScore: " + journorating.getScore());
		System.out.println("RS > journorating.getComment: " + journorating.getComment());
		
		boolean status = rd.createRating(journorating);
		return status;
	}
	
	public JournoRating getRating(JournoRating journorating) {
		return rd.getRating(journorating);
	}
	
	public boolean updateRating(JournoRating journorating) {
		boolean status = rd.updateRating(journorating);
		return status;
	}
	
	public boolean deleteRating(JournoRating journorating) {
		boolean status = rd.deleteRating(journorating);
		return status;
	}
	
	/*
	 * Supporting business-logic stuff
	 */

	// Nothing yet.

}
