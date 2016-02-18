package com.devcru.journowatch.api.objects;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Monitored on 12/27/2015. Journo model class (POJO)
 */

public class Journo {

	@JsonProperty("uuid")
	private UUID uuid = null;
	@JsonProperty("name")
	private String name = null;
	@JsonProperty("status")
	private String status = null; // enum?
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
