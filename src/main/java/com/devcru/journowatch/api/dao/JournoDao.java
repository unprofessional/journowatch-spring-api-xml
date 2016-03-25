package com.devcru.journowatch.api.dao;

import java.util.LinkedList;

import com.devcru.journowatch.api.objects.Journo;

public interface JournoDao {
	
	public boolean createJourno(Journo journo);
	
	public Journo getJourno(Journo journo);
	
	public boolean updateJourno(Journo journo);
	
	public boolean deleteJourno(Journo journo);
	
	public LinkedList<Journo> getAllJournos();

}
