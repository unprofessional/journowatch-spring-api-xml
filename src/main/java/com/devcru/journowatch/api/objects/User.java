package com.devcru.journowatch.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Monitored on 12/25/2015. User model class (POJO)
 */

public class User {

	@JsonProperty("username")
	private String username = null;
	@JsonProperty("email")
	private String email = null;
	@JsonProperty("firstname")
	private String firstname = null;
	@JsonProperty("lastname")
	private String lastname = null;
	@JsonProperty("role")
	private String role = null; // Separate into relationship-table later?
	@JsonProperty("password")
	private String password = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
