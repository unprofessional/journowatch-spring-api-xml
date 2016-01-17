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
	
	/*
	 * CRUD stuff
	 */
	
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
	
	/*
	 * Supporting business-logic stuff
	 */

	// TODO: isSelf()??? (this maintains state)
	public boolean doSomething() {
		return false;
	}
	
	/* TODO: Apply rules with isSelf()???
	 * if(isSelf()) { updateUser(); } else // do nothing
	 * 
	 * TODO: User operations
	 * public static void verifyCredentials(User user) { //... }
	 * 
	 * public static void submitRating(User user, Rating rating, Journo journo) { //... }
	 * 
	 * public static void doSomethingElse() {}
	 * 
	 */
	
	public static boolean verifyCredentials(User user) {
		boolean isSuccess = false;
		
		// isSuccess = userServ.verifyCredentials(user);
		
		return isSuccess;
	}
	
}
