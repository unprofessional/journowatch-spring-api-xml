package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcru.journowatch.api.daoimpl.VenueDaoImpl;
import com.devcru.journowatch.api.objects.Venue;

@Service
public class VenueService {
	
	@Autowired
	private VenueDaoImpl vd;
	
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

}
