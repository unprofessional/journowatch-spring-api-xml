package com.devcru.journowatch.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devcru.journowatch.api.objects.Rating;
import com.devcru.journowatch.api.services.RatingService;

@Controller
@RequestMapping(value = "/rating/*")
public class RatingController {
	
	private RatingService ratingServ;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void createRating(@RequestBody Rating rating) {
		ratingServ.createRating(rating);
	}

}
