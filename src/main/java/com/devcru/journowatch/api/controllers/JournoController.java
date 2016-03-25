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
import com.devcru.journowatch.api.objects.Journo;
import com.devcru.journowatch.api.objects.Partnership;
import com.devcru.journowatch.api.objects.JournoRating;
import com.devcru.journowatch.api.services.JournoService;
import com.devcru.journowatch.api.services.PartnershipService;

/**
 * Created by Monitored on 12/25/2015.
 * Journo Controller Class
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
@RequestMapping(value="/journo/*")
public class JournoController {
	
	@Autowired
    private JournoService journoServ;
	@Autowired
	private PartnershipService partnershipServ;
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public String createJourno(@RequestBody Journo journo) {
		
		// DEBUG BEGIN
		String fullname = journo.getFullname();
		int status = journo.getStatus();
		int overallscore = journo.getOverallScore();
		String bio = journo.getBio();

		System.out.println("JC > fullname: " + fullname);
		System.out.println("JC > status: " + status);
		System.out.println("JC > overallscore: " + overallscore);
		System.out.println("JC > bio: " + bio);
		// END DEBUG
		
		boolean isSuccess = journoServ.createJourno(journo);
		
		System.out.println("JC > isSuccess: " + isSuccess);
		
		return isSuccess + "";
	}
	
	@RequestMapping(value="/{journofullname}", method=RequestMethod.GET)
	@ResponseBody
	public Journo getJourno(@PathVariable("journofullname") String fullname) {
		Journo journo = new Journo();
		journo.setFullname(fullname);
		journoServ.getJourno(journo);
		return journo;
	}
	
	@RequestMapping(value="/{journofullname}", method=RequestMethod.PUT)
	@ResponseBody
	public boolean updateJourno(@PathVariable("journofullname") String fullname, @RequestBody Journo journo) {
		journo.setFullname(fullname);
		boolean isSuccess = journoServ.updateJourno(journo);
		return isSuccess;
	}
	
	@RequestMapping(value="/{journofullname}", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteJourno(@PathVariable("journofullname") String fullname) {
		Journo journo = new Journo();
		journo.setFullname(fullname);
		boolean isSuccess = journoServ.deleteJourno(journo);
		return isSuccess;
	}
	
	/* Supporting endponts */
	
	@RequestMapping(value="/{juuid}/venues", method=RequestMethod.GET)
	@ResponseBody
	public LinkedList<Partnership> getPartnership(@PathVariable("juuid") UUID juuid) {
		LinkedList<Partnership> partnerships = new LinkedList<Partnership>();
		Partnership partnership = new Partnership();
		partnership.setJournouuid(juuid);
		partnershipServ.getAllPartnerships(partnership);
		return partnerships;
	}
	
	@RequestMapping(value="/{juuid}/venue/{vuuid}", method=RequestMethod.GET)
	@ResponseBody
	public Partnership getPartnership(@PathVariable("juuid") UUID juuid, @PathVariable("vuuid") UUID vuuid) {		
		Partnership partnership = new Partnership();
		partnership.setJournouuid(juuid);
		partnership.setVenueuuid(vuuid);
		partnership = partnershipServ.getPartnership(partnership);
		return partnership;
	}
	
	// TODO: all journoratings?
	
	@RequestMapping(value="/juuid/journoratings", method=RequestMethod.GET)
	@ResponseBody
	public LinkedList<JournoRating> getRatings(@PathVariable("") UUID uuid) {
		LinkedList<JournoRating> journorating = null;
		return journorating;
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	@ResponseBody
	public LinkedList<Journo> getAllJournos() {
		LinkedList<Journo> journos = new LinkedList<Journo>();
		journos = journoServ.getAllJournos();
		return journos;
	}

}
