package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcru.journowatch.api.daoimpl.VenueDaoImpl;
import com.devcru.journowatch.api.objects.Venue;

/**
 * @author Monitored We are going to make use of a "Service layer" this time
 *         around to separate business logic from the presentation layer
 *         (Controllers)
 *
 */

@Service
public class VenueService {

	@Autowired
	private VenueDaoImpl vd;
	
	/*
	 * CRUD stuff
	 */

	public boolean createVenue(Venue venue) {
		boolean isSuccess = vd.createVenue(venue);
		return isSuccess;
	}

	public Venue getVenue(Venue venue) {
		venue = vd.getVenue(venue);
		return venue;
	}

	public boolean updateVenue(Venue venue) {
		boolean isSuccess = vd.updateVenue(venue);
		return isSuccess;
	}

	public boolean deleteVenue(Venue venue) {
		boolean isSuccess = vd.deleteVenue(venue);
		return isSuccess;
	}

	/*
	 * Supporting business-logic stuff
	 */

	// Nothing yet.

}
