package com.devcru.journowatch.api.objects;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JournoRating {

	@JsonProperty("uuid")
	private UUID uuid;
	@JsonProperty("timestamp")
	private Timestamp timestamp;
	@JsonProperty("status") // user banned/inactive/deleted, etc
	private int status;
	@JsonProperty("owneruuid")
	private UUID owneruuid; // UUID of the user who owns this journorating
	@JsonProperty("journouuid")
	private UUID journouuid; // UUID of the journo for whom this applies
	@JsonProperty("score")
	private int score; // (1-100?)
	@JsonProperty("headline")
	private String headline;
	@JsonProperty("comment")
	private String comment;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UUID getOwneruuid() {
		return owneruuid;
	}

	public void setOwneruuid(UUID owneruuid) {
		this.owneruuid = owneruuid;
	}

	public UUID getJournouuid() {
		return journouuid;
	}

	public void setJournouuid(UUID journouuid) {
		this.journouuid = journouuid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
