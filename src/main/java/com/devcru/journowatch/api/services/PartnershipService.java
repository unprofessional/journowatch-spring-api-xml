package com.devcru.journowatch.api.services;

import java.util.LinkedList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.devcru.journowatch.api.daoimpl.PartnershipDaoImpl;
import com.devcru.journowatch.api.objects.Partnership;

/**
 * @author Monitored We are going to make use of a "Service layer" this time
 *         around to separate business logic from the presentation layer
 *         (Controllers)
 *
 */

@Service
public class PartnershipService {
	
	@Autowired
	private PartnershipDaoImpl pd;
	
	/*
	 * CRUD stuff
	 */
	
	public boolean createPartnership(Partnership partnership){
		boolean isSuccess = pd.createPartnership(partnership);
		return isSuccess;
	}
	
	public Partnership getPartnership(Partnership partnership) {
		partnership = pd.getPartnership(partnership);
		return partnership;
	}
	
	public boolean updatePartnership(Partnership partnership) {
		boolean isSuccess = pd.updatePartnership(partnership);
		return isSuccess;
	}
	
	public boolean deletePartnership(Partnership partnership) {
		boolean isSuccess = pd.deletePartnership(partnership);
		return isSuccess;
	}
	
	/*
	 * Supporting business-logic stuff
	 */

	public LinkedList<Partnership> getPartnerships(Partnership partnership) {
		LinkedList<Partnership> partnerships = new LinkedList<Partnership>();
		partnerships = pd.getPartnershipsForVenue(partnership);
		return partnerships;
	}

}
