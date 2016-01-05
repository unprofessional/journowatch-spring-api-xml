package com.devcru.journowatch.api.dao;

import java.util.UUID;

import com.devcru.journowatch.api.objects.User;

/**
 * Created by Monitored on 12/25/2015.
 */

public interface UserDao {
	
	public boolean addUser(User user);
	
	public User getUserByUsername(User user); // Change this to getUser() that can check for UUID and email, too
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public UUID getUuid(String username);

    public void login();
    public void rateVenue();
    public void rateJourno();

}
