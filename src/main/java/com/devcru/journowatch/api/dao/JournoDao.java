package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.Journo;

public interface JournoDao {
	
	public boolean createJourno(Journo journo);
	
	public Journo getJourno(Journo journo);
	
	public boolean updateJourno(Journo journo);
	
	public boolean deleteJourno(Journo journo);

}
