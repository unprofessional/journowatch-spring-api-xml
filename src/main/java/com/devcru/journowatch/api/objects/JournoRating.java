package com.devcru.journowatch.api.objects;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JournoRating {

	// TODO: Consider making a status field

	@JsonProperty("uuid")
	private UUID uuid;
	@JsonProperty("timestamp")
	private Timestamp timestamp;
	@JsonProperty("owneruuid")
	private UUID owneruuid; // UUID of the user who owns this journorating
	@JsonProperty("journouuid")
	private UUID journouuid; // UUID of the journo for whom this applies
	@JsonProperty("score")
	private int score; // (1-100?)
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
