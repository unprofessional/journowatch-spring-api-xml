package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.JournoRating;

public interface JournoRatingDao {
	
	public boolean createRating(JournoRating journorating);
	
	public JournoRating getRating(JournoRating journorating);
	
	// Probably going to be the most under-used method...
	// Consider removing entirely.
	public boolean updateRating(JournoRating journorating);
	
	public boolean deleteRating(JournoRating journorating);

}
