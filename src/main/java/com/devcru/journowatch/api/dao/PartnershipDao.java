package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.Journo;
import com.devcru.journowatch.api.objects.Partnership;
import com.devcru.journowatch.api.objects.Venue;

public interface PartnershipDao {
	
	public boolean createPartnership(Partnership partnership);
	
	// This is meant for both Journo and Venue resources to access
	public Partnership getPartnership(Journo journo, Venue venue);
	
	// This is the "true" method that consolidates the above
	public Partnership getPartnershipViaUuid(Partnership partnership);
	
	public boolean updatePartnership(Partnership partnership);
	
	public boolean deletePartnership(Partnership partnership);

}
