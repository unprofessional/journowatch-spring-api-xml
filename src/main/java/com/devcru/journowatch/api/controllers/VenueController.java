package com.devcru.journowatch.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devcru.journowatch.api.objects.Venue;
import com.devcru.journowatch.api.services.VenueService;

@Controller
@RequestMapping(value="/venue/*")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public boolean createService(@RequestBody Venue venue) {
		boolean isSuccess = venueService.createVenue(venue);
		return isSuccess;
	}
	
	@RequestMapping(value="/{vuuid}", method=RequestMethod.GET)
	@ResponseBody
	public Venue getVenue(@PathVariable("vuuid") UUID uuid) {
		Venue venue = new Venue();
		venue.setUuid(uuid);
		venue = venueService.getVenue(venue);
		return venue;
	}
	
	@RequestMapping(value="/{vuuid}", method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateVenue(@PathVariable("vuuid") UUID uuid, @RequestBody Venue venue) {
		venue.setUuid(uuid);
		boolean isSuccess = venueService.updateVenue(venue);
		return isSuccess;
	}
	
	@RequestMapping(value="/{vuuid}", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteVenue(@PathVariable("vuuid") UUID uuid) {
		Venue venue = new Venue();
		venue.setUuid(uuid);
		boolean isSuccess = venueService.deleteVenue(venue);
		return isSuccess;
	}

}
