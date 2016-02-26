package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcru.journowatch.api.daoimpl.PartnershipDaoImpl;
import com.devcru.journowatch.api.objects.Partnership;

@Service
public class PartnershipService {
	
	@Autowired
	private PartnershipDaoImpl pd;
	
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

}
