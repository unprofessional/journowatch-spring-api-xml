package com.devcru.journowatch.api.dao;

import com.devcru.journowatch.api.objects.User;

/**
 * Created by Monitored on 12/25/2015.
 */

public interface UserDao {
	
	public boolean addUser(User user);
	
	public void getUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);

    public void login();
    public void rateVenue();
    public void rateJourno();

}
