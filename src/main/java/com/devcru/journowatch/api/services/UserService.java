package com.devcru.journowatch.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcru.journowatch.api.daoimpl.UserDaoImpl;
import com.devcru.journowatch.api.objects.User;

/**
 * @author Monitored
 * We are going to make use of a "Service layer" this time around
 * to separate business logic from the presentation layer (Controllers)
 *
 */

@Service
public class UserService {
	
	@Autowired
	private static UserDaoImpl ud;
	
	public boolean createUser(User user) {
		boolean status = ud.addUser(user);
		return status;
	}
	
	public User getUser(User user) {
		// TODO: set username/uuid/email
		user = ud.getUserByUsername(user);
		return user;
	}
	
	public boolean updateUser(User user) {
		boolean status = ud.updateUser(user);
		return status;
	}
	
	public boolean deleteUser(User user) {
		boolean status = ud.deleteUser(user);
		return status;
	}

}
