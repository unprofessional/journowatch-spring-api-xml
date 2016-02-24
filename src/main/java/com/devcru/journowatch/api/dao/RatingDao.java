package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.Journo;
import com.devcru.journowatch.api.objects.Rating;
import com.devcru.journowatch.api.objects.Venue;

public interface RatingDao {
	
	public boolean createRating(Rating rating);
	
	public Rating getRating(Rating rating);
	
	public Rating getRating(Journo journo, Venue venue);
	
	public boolean updateRating(Rating rating);
	
	public boolean deleteRating(Rating rating);

}
