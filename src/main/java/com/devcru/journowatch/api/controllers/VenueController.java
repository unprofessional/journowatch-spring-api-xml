package com.devcru.journowatch.api.controllers;

import java.util.LinkedList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devcru.journowatch.api.objects.Partnership;
import com.devcru.journowatch.api.objects.Venue;
import com.devcru.journowatch.api.services.PartnershipService;
import com.devcru.journowatch.api.services.VenueService;

@Controller
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
		partnerships = partnershipServ.getPartnerships(partnership);
		return partnerships;
	}

}
