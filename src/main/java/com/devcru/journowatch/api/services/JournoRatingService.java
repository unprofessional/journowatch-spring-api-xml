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
	private JournoRatingDaoImpl jrd;
	
	/*
	 * CRUD stuff
	 */
	
	public boolean createRating(JournoRating journorating) {
		
		System.out.println("JRS > journorating.getUuid: " + journorating.getUuid());
		System.out.println("JRS > journorating.getTimestamp: " + journorating.getTimestamp());
		System.out.println("JRS > journorating.getStatus: " + journorating.getStatus());
		System.out.println("JRS > journorating.getOwner: " + journorating.getOwneruuid());
		System.out.println("JRS > journorating.getJourno: " + journorating.getJournouuid());
		System.out.println("JRS > journorating.getScore: " + journorating.getScore());
		System.out.println("JRS > journorating.getHeadline: " + journorating.getHeadline());
		System.out.println("JRS > journorating.getComment: " + journorating.getComment());
		
		boolean status = jrd.createRating(journorating);
		return status;
	}
	
	public JournoRating getRating(JournoRating journorating) {
		return jrd.getRating(journorating);
	}
	
	public boolean updateRating(JournoRating journorating) {
		boolean status = jrd.updateRating(journorating);
		return status;
	}
	
	public boolean deleteRating(JournoRating journorating) {
		boolean status = jrd.deleteRating(journorating);
		return status;
	}
	
	/*
	 * Supporting business-logic stuff
	 */

	// Nothing yet.

}
