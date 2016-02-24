package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.Rating;

public interface RatingDao {
	
	public boolean createRating(Rating rating);
	
	public Rating getRating(Rating rating);
	
	public boolean updateRating(Rating rating);
	
	public boolean deleteRating(Rating rating);

}
