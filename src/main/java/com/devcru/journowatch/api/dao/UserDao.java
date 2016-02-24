package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.User;

/**
 * Created by Monitored on 12/25/2015.
 */

public interface UserDao {
	
	public boolean createUser(User user);
	
	public User getUser(User user); // TODO: Check for UUID and email, too
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);
	
	// Utility methods
	//public User getUserViaUsername(String username);

	// Maybe methods?
    public void login();
    public void rateVenue();
    public void rateJourno();

}
