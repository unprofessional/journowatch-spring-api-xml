package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcru.journowatch.api.daoimpl.JournoDaoImpl;
import com.devcru.journowatch.api.objects.Journo;

/**
 * @author Monitored We are going to make use of a "Service layer" this time
 *         around to separate business logic from the presentation layer
 *         (Controllers)
 *
 */

@Service
public class JournoService {

	@Autowired
	private JournoDaoImpl jd;

	/*
	 * CRUD stuff
	 */

	public boolean createJourno(Journo journo) {

		// DEBUG BEGIN
		String fullname = journo.getFullname();
		String status = journo.getStatus();
		int overallscore = journo.getOverallScore();
		String bio = journo.getBio();

		System.out.println("JS > fullname: " + fullname);
		System.out.println("JS > status: " + status);
		System.out.println("JS > overallscore: " + overallscore);
		System.out.println("JS > bio: " + bio);
		// END DEBUG

		boolean isSuccess = jd.createJourno(journo);

		System.out.println("JS > isSuccess: " + isSuccess);

		return isSuccess;

	}

	public Journo getJourno(Journo journo) {
		journo = jd.getJourno(journo);
		return journo;
	}
	
	public boolean updateJourno(Journo journo) {
		boolean isSuccess = jd.updateJourno(journo);
		return isSuccess;
	}
	
	public boolean deleteJourno(Journo journo) {
		boolean isSuccess = jd.deleteJourno(journo);
		return isSuccess;
	}
	
	/*
	 * Supporting business-logic stuff
	 */
	
	// Can't think of anything right now.  Will come back to this as needed.
	
}
