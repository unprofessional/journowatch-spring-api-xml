package com.devcru.journowatch.api.objects;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Monitored on 12/27/2015. Journo model class (POJO)
 */

public class Journo {

	@JsonProperty("uuid")
	private UUID uuid = null;
	@JsonProperty("fullname")
	private String fullname = null;
	@JsonProperty("status")
	private int status = -1; // enum?
	@JsonProperty("bio")
	private String bio = null;
	@JsonProperty("overallscore")
	private int overallScore = -1; // (0-100 scale)

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(int overallScore) {
		this.overallScore = overallScore;
	}

}
