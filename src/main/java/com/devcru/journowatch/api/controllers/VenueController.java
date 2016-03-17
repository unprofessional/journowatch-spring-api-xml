package com.devcru.journowatch.api.controllers;

import java.util.LinkedList;
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
import com.devcru.journowatch.api.objects.Partnership;
import com.devcru.journowatch.api.objects.Venue;
import com.devcru.journowatch.api.services.PartnershipService;
import com.devcru.journowatch.api.services.VenueService;

/**
 * Created by Monitored on 12/25/2015.
 * Venue Controller Class
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
@RequestMapping(value="/venue/*")
public class VenueController {
	
	@Autowired
	private VenueService venueServ;
	@Autowired
	private PartnershipService partnershipServ;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public boolean createService(@RequestBody Venue venue) {
		boolean isSuccess = venueServ.createVenue(venue);
		return isSuccess;
	}
	
	@RequestMapping(value="/{vuuid}", method=RequestMethod.GET)
	@ResponseBody
	public Venue getVenue(@PathVariable("vuuid") UUID uuid) {
		Venue venue = new Venue();
		venue.setUuid(uuid);
		venue = venueServ.getVenue(venue);
		return venue;
	}
	
	@RequestMapping(value="/{vuuid}", method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateVenue(@PathVariable("vuuid") UUID uuid, @RequestBody Venue venue) {
		venue.setUuid(uuid);
		boolean isSuccess = venueServ.updateVenue(venue);
		return isSuccess;
	}
	
	@RequestMapping(value="/{vuuid}", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteVenue(@PathVariable("vuuid") UUID uuid) {
		Venue venue = new Venue();
		venue.setUuid(uuid);
		boolean isSuccess = venueServ.deleteVenue(venue);
		return isSuccess;
	}
	
	/* Supporting endpoints */
	
	@RequestMapping(value="/{vuuid}/journo/{juuid}", method=RequestMethod.GET)
	@ResponseBody
	public Partnership getPartnership(@PathVariable("vuuid") UUID vuuid, @PathVariable("juuid") UUID juuid) {		
		Partnership partnership = new Partnership();
		partnership.setJournouuid(juuid);
		partnership.setVenueuuid(vuuid);
		partnership = partnershipServ.getPartnership(partnership);
		return partnership;
	}
	
	@RequestMapping(value="/{vuuid}/partnerships", method=RequestMethod.GET)
	@ResponseBody
	public LinkedList<Partnership> getPartnerships(@PathVariable("vuuid") UUID vuuid) {
		LinkedList<Partnership> partnerships = new LinkedList<Partnership>();
		Partnership partnership = new Partnership();
		partnership.setVenueuuid(vuuid);
		partnerships = partnershipServ.getAllPartnerships(partnership);
		return partnerships;
	}
	
	// FIXME: Find out a better URI convention for "get all" requests
	@RequestMapping(value="/all", method=RequestMethod.GET)
	@ResponseBody
	public LinkedList<Venue> showAllVenues() {
		
		LinkedList<Venue> venues = new LinkedList<Venue>();
		
		venues = venueServ.getAllVenues();
		
		return venues;
	}

}
