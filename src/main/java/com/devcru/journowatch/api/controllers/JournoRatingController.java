package com.devcru.journowatch.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devcru.journowatch.api.constants.Constants;
import com.devcru.journowatch.api.objects.JournoRating;
import com.devcru.journowatch.api.services.JournoRatingService;

/**
 * Created by Monitored on 02/23/2016.
 * JournoRating Controller Class
 */

@Controller
@CrossOrigin( // CORS... it's not just a shitty beer
		origins = {
				// use "https://" when appropriate
				"http://" + Constants.LOCALHOST8080,
				"http://" + Constants.OPENSHIFT
		},
		methods = {
				RequestMethod.GET,
				RequestMethod.POST,
				RequestMethod.PUT,
				RequestMethod.DELETE,
				RequestMethod.OPTIONS // for the browser CORS pre-flight
		},
		allowedHeaders = "*" // TODO: Find the minimum-required headers necessary instead of allowing all
)
@RequestMapping(value = "/journorating/*")
public class JournoRatingController {
	
	@Autowired
	private JournoRatingService ratingServ;
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public boolean createRating(@RequestBody JournoRating journorating) {
		
		System.out.println("RC > journorating.getUuid: " + journorating.getUuid());
		System.out.println("RC > journorating.getTimestamp: " + journorating.getTimestamp());
		System.out.println("RC > journorating.getOwner: " + journorating.getOwneruuid());
		System.out.println("RC > journorating.getJourno: " + journorating.getJournouuid());
		System.out.println("RC > journorating.getScore: " + journorating.getScore());
		System.out.println("RC > journorating.getComment: " + journorating.getComment());
		
		boolean isSuccess = ratingServ.createRating(journorating);
		return isSuccess;
	}
	
	@RequestMapping(value="/{ratinguuid}", method = RequestMethod.GET)
	@ResponseBody
	public JournoRating getRating(@PathVariable("ratinguuid") UUID uuid) {
		JournoRating journorating = new JournoRating();
		journorating.setUuid(uuid);
		journorating = ratingServ.getRating(journorating); // Will need to get it via UUID
		return journorating;
	}
	
	// XXX: TEST ENDPOINT FOR RatingDaoImpl LOGIC
	@RequestMapping(value="/{ouuid}/{juuid}", method=RequestMethod.GET)
	@ResponseBody
	public JournoRating getRating(@PathVariable("ouuid") UUID ouuid, @PathVariable("juuid") UUID juuid) {
		JournoRating journorating = new JournoRating();
		
		journorating.setOwneruuid(ouuid);
		journorating.setJournouuid(juuid);
		
		ratingServ.getRating(journorating);
		
		return journorating;
	}
	
	@RequestMapping(value="/{ratinguuid}", method = RequestMethod.PUT)
	@ResponseBody
	public boolean updateRating(@PathVariable("ratinguuid") UUID uuid, @RequestBody JournoRating journorating) {
		journorating.setUuid(uuid);
		boolean isSuccess = ratingServ.updateRating(journorating);
		return isSuccess;
	}
	
	@RequestMapping(value="/{ratinguuid}", method = RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteRating(@PathVariable("ratinguuid") UUID uuid) {
		JournoRating journorating = new JournoRating();
		journorating.setUuid(uuid);
		boolean isSuccess = ratingServ.deleteRating(journorating);
		return isSuccess;
	}

}
