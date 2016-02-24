package com.devcru.journowatch.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devcru.journowatch.api.objects.Partnership;
import com.devcru.journowatch.api.services.PartnershipService;

@Controller
@RequestMapping(value="/partnership/*")
public class PartnershipController {
	
	@Autowired
	private PartnershipService partnershipService;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public boolean createPartnership(@RequestBody Partnership partnership) {
		boolean isSuccess = partnershipService.createPartnership(partnership);
		return isSuccess;
	}
	
	// Consolidation/redirected-to endpoint for journo/{juuid}/venue/{vuuid} endpoints
	@RequestMapping(value="/{puuid}", method=RequestMethod.GET)
	@ResponseBody
	public Partnership getPartnership(@PathVariable("puuid") UUID uuid) {
		Partnership partnership = new Partnership();
		partnership.setUuid(uuid);
		partnershipService.getPartnership(partnership);		
		return partnership;
	}
	
	@RequestMapping(value="/{puuid}", method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateParntership(@PathVariable("puuid") UUID uuid, @RequestBody Partnership partnership) {
		partnership.setUuid(uuid);
		boolean isSuccess = partnershipService.updatePartnership(partnership);
		return isSuccess;
	}
	
	@RequestMapping(value="/{puuid}", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deletePartnership(@PathVariable("puuid") UUID uuid) {
		Partnership partnership = new Partnership();
		partnership.setUuid(uuid);
		boolean isSuccess = partnershipService.deletePartnership(partnership);
		return isSuccess;
	}

}
