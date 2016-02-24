package com.devcru.journowatch.api.objects;

import java.util.UUID;

/**
 * Created by Monitored on 12/27/2015. Venue model class (POJO)
 */

public class Venue {

	private UUID uuid;
	private String name;
	private int status; // Make sure to check DB table that this datatype is correct
	private int overallscore;
	private String bio;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOverallscore() {
		return overallscore;
	}

	public void setOverallscore(int overallscore) {
		this.overallscore = overallscore;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}
