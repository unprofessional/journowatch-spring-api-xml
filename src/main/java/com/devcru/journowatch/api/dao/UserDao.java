package com.devcru.journowatch.api.dao;

import java.util.UUID;

import com.devcru.journowatch.api.objects.User;

/**
 * Created by Monitored on 12/25/2015.
 */

public interface UserDao {
	
	public boolean createUser(User user);
	
	public User getUserByUsername(User user); // Change this to getUser() that can check for UUID and email, too
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(User user);
	
	// Utility methods
	public UUID getUuid(String username);

	// Maybe methods?
    public void login();
    public void rateVenue();
    public void rateJourno();

}
