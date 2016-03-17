package com.devcru.journowatch.api.dao;

import java.util.LinkedList;

import com.devcru.journowatch.api.objects.Venue;

public interface VenueDao {
	
	public boolean createVenue(Venue venue);
	
	public Venue getVenue(Venue venue);
	
	public boolean updateVenue(Venue venue);
	
	public boolean deleteVenue(Venue venue);
	
	public LinkedList<Venue> getAllVenues();

}
