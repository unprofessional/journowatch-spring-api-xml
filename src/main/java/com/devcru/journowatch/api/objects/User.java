package com.devcru.journowatch.api.objects;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Monitored on 12/25/2015. User model class (POJO)
 */

public class User {

	@JsonProperty("uuid")
	private UUID uuid = null;
	@JsonProperty("username")
	private String username = null;
	@JsonProperty("email")
	private String email = null;
	@JsonProperty("firstname")
	private String firstName = null;
	@JsonProperty("lastname")
	private String lastName = null;
	@JsonProperty("role")
	private int role = 2; // Default 2 for ROLE_USER (1 is ROLE_ADMIN)
	@JsonProperty("password")
	private String password = null;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

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
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
