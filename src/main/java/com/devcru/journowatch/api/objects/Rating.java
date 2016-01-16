package com.devcru.journowatch.api.objects;

import java.sql.Timestamp;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {

	// TODO: Consider making a status field

	@JsonProperty("uuid")
	private UUID uuid;
	@JsonProperty("timestamp")
	private Timestamp timestamp;
	@JsonProperty("owneruuid")
	private UUID owner; // UUID of the user who owns this rating
	@JsonProperty("journouuid")
	private UUID journo; // UUID of the journo for whom this applies
	@JsonProperty("rating")
	private int rating; // (1-100?)
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

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public UUID getJourno() {
		return journo;
	}

	public void setJourno(UUID journo) {
		this.journo = journo;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
