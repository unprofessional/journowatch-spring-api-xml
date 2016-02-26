package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.Partnership;

public interface PartnershipDao {
	
	public boolean createPartnership(Partnership partnership);
	
	public Partnership getPartnership(Partnership partnership);
	
	public boolean updatePartnership(Partnership partnership);
	
	public boolean deletePartnership(Partnership partnership);

}
