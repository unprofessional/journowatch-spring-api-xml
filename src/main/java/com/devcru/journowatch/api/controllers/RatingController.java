package com.devcru.journowatch.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devcru.journowatch.api.objects.Rating;
import com.devcru.journowatch.api.services.RatingService;

@Controller
@RequestMapping(value = "/rating/*")
public class RatingController {
	
	@Autowired
	private RatingService ratingServ;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public boolean createRating(@RequestBody Rating rating) {
		boolean isSuccess = ratingServ.createRating(rating);
		return isSuccess;
	}
	
	@RequestMapping(value="/{ratinguuid}", method = RequestMethod.GET)
	@ResponseBody
	public Rating getRating(@PathVariable("ratinguuid") UUID uuid) {
		Rating rating = new Rating();
		rating.setUuid(uuid);
		rating = ratingServ.getRating(rating); // Will need to get it via UUID
		return rating;
	}
	
	@RequestMapping(value="/{ratinguuid}", method = RequestMethod.PUT)
	@ResponseBody
	public boolean updateRating(@PathVariable("ratinguuid") UUID uuid, @RequestBody Rating rating) {
		rating.setUuid(uuid);
		boolean isSuccess = ratingServ.updateRating(rating);
		return isSuccess;
	}
	
	@RequestMapping(value="/{ratinguuid}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteRating(@PathVariable("ratinguuid") UUID uuid) {
		Rating rating = new Rating();
		rating.setUuid(uuid);
		boolean isSuccess = ratingServ.deleteRating(rating);
		return isSuccess;
	}

}
